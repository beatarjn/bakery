package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BunsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BunsApplication.class, args);
        System.out.println("Buns store application started!");
    }
}