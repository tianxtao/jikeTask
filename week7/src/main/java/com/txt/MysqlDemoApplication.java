package com.txt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.txt.mapper")
public class MysqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlDemoApplication.class, args);
    }
}
