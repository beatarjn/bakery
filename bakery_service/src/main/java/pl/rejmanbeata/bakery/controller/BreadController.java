package pl.rejmanbeata.bakery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rejmanbeata.bakery.model.bread.Bread;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/breads")
public class BreadController {

    @PostMapping(value = "/acceptBread", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> acceptBread(@RequestBody Bread bread) {
        System.out.println(bread);
        return new ResponseEntity<>("Bread " + bread.getBreadPrice().toString(), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("Hello World from Bakery!", OK);
    }

}