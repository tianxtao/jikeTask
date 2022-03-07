package com.txt.tccserverb.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * date       : 2022/3/6
 * description: xxx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountTemp {
    private int id;
    private String transId;
    private String fromAccount;
    private String toAccount;
    private long createTime;
    private double amount;
}
