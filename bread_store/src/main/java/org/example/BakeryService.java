package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BakeryService {

    private FeignClient feignClient;

    public BakeryService(@Autowired FeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public String getBread() {
        log.info("Connect to bakery");
        return feignClient.getBakery();
    }

    public String acceptBread(Bread request) {
        log.info("accept bread in bakery service");
        return feignClient.acceptBread(request);
    }
}
