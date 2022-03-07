package com.txt.tccserverb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TccServerBApplication {

    public static void main(String[] args) {
        SpringApplication.run(TccServerBApplication.class, args);
    }

}
