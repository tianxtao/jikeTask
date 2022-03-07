package com.txt.tccclient;

import com.txt.tccclient.rpc.BankAClient;
import com.txt.tccclient.rpc.BankBClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TccClientApplicationTests {

    @Autowired
    private BankAClient bankAClient;

    @Autowired
    private BankBClient bankBClient;

    @Test
    void contextLoads() {
    }

    @Test
    public void testTransfer(){
        bankAClient.transfer(100.00, "336", "633");
        bankBClient.transfer(900.00, "633", "336");
    }
}
