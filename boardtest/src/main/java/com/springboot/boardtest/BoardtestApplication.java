package com.springboot.boardtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BoardtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardtestApplication.class, args);
    }

}
