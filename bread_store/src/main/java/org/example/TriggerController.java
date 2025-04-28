package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/trigger")
public class TriggerController {

    private BakeryService bakeryService;

    public TriggerController(BakeryService bakeryService) {
        this.bakeryService = bakeryService;
    }

    @GetMapping
    public String getBakery() {
        return bakeryService.getBread();
    }

    @PostMapping("/post")
    public String postBread(@RequestBody Bread bread) {
        log.info("trigger post");
        return bakeryService.acceptBread(bread);
    }
}
