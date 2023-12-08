package com.example.hk_ht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@MapperScan("com.example.hk_ht.Mapper")
public class HkHtApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkHtApplication.class, args);
    }

}
