package pl.rejmanbeata.bakery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rejmanbeata.bakery.model.bread.Bread;

@RestController
@RequestMapping("/breads")
public class BreadController {

    @PostMapping
    public ResponseEntity<String> acceptBread(@RequestBody Bread bread){
        System.out.println(bread);
        return new ResponseEntity<>("bread", HttpStatus.OK);
    }


}
