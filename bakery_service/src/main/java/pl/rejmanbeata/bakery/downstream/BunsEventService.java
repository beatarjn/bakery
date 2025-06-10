package pl.rejmanbeata.bakery.downstream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BunsEventService {

    public void process(String message) {
        log.info("EventService: {}", message);
    }

}
