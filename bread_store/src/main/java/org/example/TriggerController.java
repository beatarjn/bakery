package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
