package com.txt.banktransferbank2.service.imp;

import com.txt.banktransferap.api.Bank1Service;
import com.txt.banktransferap.api.Bank2Service;
import com.txt.banktransferbank2.service.AccountService;
import com.txt.banktransferorm.bean.AccountInfo;
import com.txt.banktransferorm.bean.AccountTemp;
import com.txt.banktransferorm.dao.AccountInfoDao;
import lombok.extern.log4j.Log4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.dromara.hmily.common.exception.HmilyRuntimeException;
import org.dromara.hmily.core.context.HmilyContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * date       : 2022/3/7
 * description: xxx
 */
@Component
@Log4j
public class AccountServiceImp implements AccountService {
    @DubboReference(version = "1.0.0")
    Bank1Service bank1Service;

    @Autowired
    private AccountInfoDao accountInfoDao;

    /**
     * @date  : 2022/3/7
     * @desc  : try阶段的操作，完成业务检查，资源预留，扣款
     * @param : accountNo
     * @param : amount
     * @return: void
     */
    @Transactional
    @HmilyTCC(confirmMethod = "commit", cancelMethod = "rollback")
    @Override
    public void transfer(String fromAccount, double amount, String toAccount,int accountType) {
        //事务id
        String transId = HmilyContextHolder.get().getTransId().toString();
        log.info("******** Bank1 Service  begin try...  "+transId );
        // 业务检查
        AccountInfo accountParam = new AccountInfo();
        accountParam.setAccountNo(fromAccount);

        double balance = accountInfoDao.queryAccountBalance(accountParam, accountType);
        AccountInfo accountInfo = accountInfoDao.queryAccountInfo(fromAccount, accountType);

        if(accountInfo == null) {
            log.error("账户不存在！");
            return;
        }

        if(balance < amount) {
            log.error("余额不足！");
            return;
        }

        // try幂等校验
        int existTry = accountInfoDao.isExistTry(transId);

        //try幂等校验
        if(existTry>0){
            log.error("******** Bank1 Service 已经执行try，无需重复执行，事务id:{}  "+transId );
            return ;
        }

        //try 悬挂处理
        if(accountInfoDao.isExistCancel(transId)>0 || accountInfoDao.isExistConfirm(transId)>0){
            log.error("******** Bank1 Service 已经执行confirm或cancel，悬挂处理，事务id:{}  "+transId );
            return ;
        }

        //从账户扣减
        if(accountInfoDao.subtractAccountBalance(amount, fromAccount, accountType)<=0){
            //扣减失败
            throw new HmilyRuntimeException("bank1 exception，扣减失败，事务id:{}"+transId);
        }

        AccountTemp accountTempParam = new AccountTemp();
        accountTempParam.setFromAccount(fromAccount);
        accountTempParam.setToAccount(toAccount);
        accountTempParam.setAmount(amount);
        accountTempParam.setCreateTime(System.currentTimeMillis());
        accountTempParam.setAccountType(accountType);

        // 加入到冻结表
        if(accountInfoDao.addAccountTemp(accountTempParam)<=0){
            // 冻结失败
            throw new HmilyRuntimeException("bank1 exception，冻结失败，事务id:{}"+transId);
        }

        //增加本地事务try成功记录，用于幂等性控制标识
        accountInfoDao.addTry(transId);

       /* //远程调用bank2
        if(!bank2Client.test2(amount,transId)){
            throw new HmilyRuntimeException("bank2Client exception，事务id:{}"+transId);
        }
        if(amount==10){//异常一定要抛在Hmily里面
            throw new RuntimeException("bank1 make exception  10");
        }*/
        log.info("******** Bank1 Service  end try...  "+transId );
    }

    @Transactional
    @Override
    public void commit() {
        String localTradeNo = HmilyContextHolder.get().getTransId().toString();
        log.info("******** Bank1 Service begin commit..."+localTradeNo );

        // 幂等校验
        if(accountInfoDao.isExistConfirm(localTradeNo) < 0) {
            log.error("******** Bank1 Service 已经执行confirm，无需重复执行，事务id:{}  "+localTradeNo );
            return ;
        }

        // 从冻结表中查出try阶段冻结的金额
        List<AccountTemp> accountTemps = accountInfoDao.queryAccountTempByTransId(localTradeNo);
        String accountNo = accountTemps.get(0).getToAccount();
        int accountType = accountTemps.get(0).getAccountType();
        double amount = 0;

        for(AccountTemp accountTemp : accountTemps) {
            amount = amount + accountTemp.getAmount();
        }

        // 调用远程接口将转账金额转账到其他账户
        bank1Service.receive(amount, accountNo, accountType);

        // 删除冻结表中的冻结记录
        accountInfoDao.deleteTemp(localTradeNo);

        //添加confirm日志，用于幂等性控制标识
        accountInfoDao.addConfirm(localTradeNo);
    }

    @Transactional
    @Override
    public void rollback() {
        //事务id
        String transId = HmilyContextHolder.get().getTransId().toString();
        log.info("******** Bank1 Service begin rollback...  " +transId);

        //空回滚处理，try阶段没有执行什么也不用做
        if(accountInfoDao.isExistTry(transId) == 0){
            log.info("******** Bank1 try阶段失败... 无需rollback "+transId );
            return;
        }

        //幂等性校验，已经执行过了，什么也不用做
        if(accountInfoDao.isExistCancel(transId) > 0){
            log.info("******** Bank1 已经执行过rollback... 无需再次rollback " +transId);
            return;
        }

        // 从冻结表中查出try阶段冻结的金额
        List<AccountTemp> accountTemps = accountInfoDao.queryAccountTempByTransId(transId);
        String accountNo = accountTemps.get(0).getFromAccount();
        int accountType = accountTemps.get(0).getAccountType();
        double amount = 0;

        for(AccountTemp accountTemp : accountTemps) {
            amount = amount + accountTemp.getAmount();
        }

        //再将金额加回账户
        accountInfoDao.addAccountBalance(amount, accountNo, accountType);

        //添加cancel日志，用于幂等性控制标识
        accountInfoDao.addCancel(transId);
        log.info("******** Bank1 Service end rollback...  " +transId);
    }

    @Transactional
    @Override
    public void addAccountBalance(String accountNo, double amount, int accountType) {
        accountInfoDao.addAccountBalance(amount, accountNo, accountType);
    }
}
