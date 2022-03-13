package com.txt.banktransferbank2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.txt.banktransferorm.dao"})
public class BankTransferBank2Application {
    public static void main(String[] args) {
        SpringApplication.run(BankTransferBank2Application.class, args);
    }
}
