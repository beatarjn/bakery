package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bread {
    private Instant breadCreationDate;
    private Instant breadExpirationDate;
    private BigDecimal breadPrice;

}
