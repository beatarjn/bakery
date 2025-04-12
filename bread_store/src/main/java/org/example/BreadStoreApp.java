package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BreadStoreApp {
    public static void main(String[] args) {
        SpringApplication.run(BreadStoreApp.class, args);
        System.out.println("Breads application started!");
    }
}