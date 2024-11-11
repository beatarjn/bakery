package pl.rejmanbeata.bakery.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RestController
@RequestMapping("/v1")
public class BakeryController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("Hello World!", OK);
    }


    @PostMapping("/bunsInfo")
    public ResponseEntity<String> createBunsInfo(@RequestBody BunsRequest bunsRequest) {
        return new ResponseEntity<>(bunsRequest.getHello(), OK);
    }

}