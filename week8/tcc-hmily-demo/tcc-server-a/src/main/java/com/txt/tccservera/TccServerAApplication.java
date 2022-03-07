package com.txt.tccservera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TccServerAApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccServerAApplication.class, args);
    }

}
