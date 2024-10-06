package pl.rejmanbeata.buns;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.*;

@SpringBootApplication
public class BunsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BunsApplication.class, args);
        out.println("Buns store application started!");
    }
}