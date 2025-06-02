package pl.rejmanbeata.buns.upstream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.rejmanbeata.buns.downstream.KafkaProducerService;

@RestController
@Slf4j
public class ProducerController {

    private KafkaProducerService kafkaProducerService;

    public ProducerController(@Autowired KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/produce")
    public ResponseEntity<Void> produce(@RequestBody KafkaMsg kafkaMsg) {
        kafkaProducerService.sendMessage(kafkaMsg.message(), kafkaMsg.key());
        log.info("Message produced");
        return ResponseEntity.ok().build();
    }
}
