package pl.rejmanbeata.buns.downstream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private String topicName = "Kasa1";


    public void sendMessage(String message, String key) {
        log.info("Sending a message: {}", message);
        kafkaTemplate.send(topicName, key, message)
                .whenCompleteAsync((c, b) -> System.out.println(c));
    }

}
