package com.txt.tccserverb.service;

/**
 * date       : 2022/3/7
 * description: xxx
 */
public interface AccountService {
    // try
    public void transfer(String fromAccount, double amount, String toAccount);

    // confirm
    public void commit();

    // concel
    public void rollback();

    public void addAccountBalance(String accountNo, double amount);

}
