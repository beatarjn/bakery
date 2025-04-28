package org.example;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.cloud.openfeign.FeignClient(url = "localhost:8070", name = "feign")
public interface FeignClient {

    @GetMapping("/breads/hello")
    String getBakery();

}
