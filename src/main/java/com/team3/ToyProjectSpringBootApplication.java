package com.team3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan ("com.team3.dao")

public class ToyProjectSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyProjectSpringBootApplication.class, args);
    }

}
