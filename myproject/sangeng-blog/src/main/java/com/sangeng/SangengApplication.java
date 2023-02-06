package com.sangeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.sangeng.mapper")
public class SangengApplication {
    public static void main(String[] args) {
        SpringApplication.run(SangengApplication.class,args);
    }
}
