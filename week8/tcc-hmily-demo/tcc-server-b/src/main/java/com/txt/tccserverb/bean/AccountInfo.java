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
public class AccountInfo {
    private int id;
    private String accountName;
    private String accountNo;
    private String accountPassword;
    private double accountBalance;
}
