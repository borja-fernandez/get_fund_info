package com.ima.get_fund_info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.ima.get_fund_info")
public class GetFundInfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GetFundInfoApplication.class, args);
    }

}