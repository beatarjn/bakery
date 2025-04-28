package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.cloud.openfeign.FeignClient(url = "localhost:8070", name = "feign")
public interface FeignClient {

    @GetMapping("/breads/hello")
    String getBakery();

    @PostMapping("breads/acceptBread")
    String acceptBread(@RequestBody Bread bread);


}
