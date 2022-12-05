package com.alpha.pandemic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.alpha.pandemic.mappers")
@EnableTransactionManagement    // 开启事务

public class PandemicApplication
{
    public static void main(String[] args) {
        SpringApplication.run(PandemicApplication.class, args);
    }
}
