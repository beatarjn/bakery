package pl.rejmanbeata.bakery.downstream;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumerService {

    private BunsEventService bunsEventService;

    @KafkaListener(
            topicPartitions = @TopicPartition(
                    topic = "Kasa1",
                    partitionOffsets = {
                            @PartitionOffset(partition = "0", initialOffset = "3"),
                            @PartitionOffset(partition = "3", initialOffset = "3")}))
    public void listenToPartition0And3(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        log.info(">> RECEIVED: [ Partition:  {}, Data: {}", partition, message);

        bunsEventService.process(message);
    }

    @KafkaListener(
            topicPartitions = @TopicPartition(
                    topic = "Kasa1",
                    partitionOffsets = {
                            @PartitionOffset(partition = "1", initialOffset = "0"),
                            @PartitionOffset(partition = "2", initialOffset = "0")})
    )
    public void listenToPartition1And2(
            @Payload String message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition
    ) {
        log.info(">> RECEIVED: [ Partition:  {}, Data: {}", partition, message);
    }
}
