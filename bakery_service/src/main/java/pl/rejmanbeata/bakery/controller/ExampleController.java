package pl.rejmanbeata.bakery.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {


    @GetMapping("/hello")
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<>("Hello World!", HttpStatus.OK);
    }


}
