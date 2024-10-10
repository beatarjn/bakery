package pl.rejmanbeata.buns;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class ExampleClientController {

    private ClientService clientService;

    @GetMapping("/sendHello")
    public String runHello() throws IOException {
        return clientService.sendHello();
    }
}