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

    public String acceptBread(Bread request) {
        System.out.println("accept bread in bakery service");
        return feignClient.acceptBread(request);
    }
}
