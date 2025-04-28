package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BakeryService {

    private FeignClient feignClient;

    public BakeryService(@Autowired FeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public String getBread() {
        System.out.println("Connect to bakery");
        return feignClient.getBakery();
    }
}
