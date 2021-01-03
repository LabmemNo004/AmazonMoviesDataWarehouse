package com.dwh.hive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.dwh.hive.dao")
public class SellApplication {

    public static void main(String[] args) {

        SpringApplication.run(SellApplication.class, args);
    }

}
