package com.txt.tccservera.dao;

import com.txt.tccservera.bean.AccountInfo;
import com.txt.tccservera.bean.AccountTemp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * date       : 2022/3/6
 * description: xxx
 */
@Mapper
@Component
public interface AccountInfoDao {
    //查询账户信息
    @Select("select * from account_info where account_no = #{accountNo};")
    AccountInfo queryAccountInfo(@Param("accountNo")String accountNo);

    // 查询账户余额
    @Select("select account_balance from account_info where account_no = #{accountNo};")
    double queryAccountBalance(@Param("accountInfo")AccountInfo accountInfo);

    //从账户表中划走金额
    @Update("update account_info set account_balance=account_balance - #{amount} where account_no=#{accountNo}; ")
    int subtractAccountBalance(@Param("amount")double amount, @Param("accountNo") String accountNo);

    // 给账户增加金额
    @Update("update account_info set account_balance=account_balance + #{amount} where account_no=#{accountNo}; ")
    int addAccountBalance(@Param("amount")double amount, @Param("accountNo") String accountNo);

    // 添加冻结金额
    @Insert("insert into account_temp(account_name, account_no, amount, create_time) values(#{accountName},#{accountNo},#{amount},#{createTime});")
    int addAccountTemp(@Param("accountTemp")AccountTemp accountTemp);

    // 删除冻结金额
    @Delete("delete from account_temp where transId = #{transId};")
    int deleteTemp(@Param("transId")String transId);

    @Select("select * from account_temp where transId = #{transId}")
    List<AccountTemp> queryAccountTempByTransId(@Param("transId") String transId);

    /**
     * 增加某分支事务try执行记录
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Insert("insert into local_try_log values(#{txNo},now());")
    int addTry(String localTradeNo);

    @Insert("insert into local_confirm_log values(#{txNo},now());")
    int addConfirm(String localTradeNo);

    @Insert("insert into local_cancel_log values(#{txNo},now());")
    int addCancel(String localTradeNo);

    /**
     * 查询分支事务try是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_try_log where tx_no = #{txNo} ")
    int isExistTry(String localTradeNo);
    /**
     * 查询分支事务confirm是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_confirm_log where tx_no = #{txNo} ")
    int isExistConfirm(String localTradeNo);

    /**
     * 查询分支事务cancel是否已执行
     * @param localTradeNo 本地事务编号
     * @return
     */
    @Select("select count(1) from local_cancel_log where tx_no = #{txNo} ")
    int isExistCancel(String localTradeNo);
}
