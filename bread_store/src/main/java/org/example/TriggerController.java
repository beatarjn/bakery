package org.example;

import org.springframework.web.bind.annotation.*;

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
        System.out.println("trigger post");
        return bakeryService.acceptBread(bread);
    }
}
