package pl.rejmanbeata.bakery.model.bread;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class Bread {

    private Instant breadCreationDate;
    private Instant breadExpirationDate;
    private BigDecimal breadPrice;

}
