package pl.rejmanbeata.buns.downstream;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.server}")
    private List<String> kafkaServer;

    @Bean
    public ProducerFactory<String, String> producerFactory(@Autowired ObjectMapper objectMapper) {
        Map<String, Object> configurationProperties = new HashMap<>();
        configurationProperties.put(BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        configurationProperties.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configurationProperties.put(VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(configurationProperties);
        producerFactory.setValueSerializer(new StringSerializer());
        producerFactory.setKeySerializer(new StringSerializer());
        return producerFactory;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(@Autowired ObjectMapper objectMapper) {
        return new KafkaTemplate<>(producerFactory(objectMapper));
    }

}
