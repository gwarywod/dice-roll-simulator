package com.avalog.dice.simulator.core.model;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StatisticDistribution {

    private Integer value;
    private BigDecimal occurrencePercent;
}
