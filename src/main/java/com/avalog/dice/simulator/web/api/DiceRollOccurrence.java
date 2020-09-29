package com.avalog.dice.simulator.web.api;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DiceRollOccurrence {
    private Integer value;
    private String occurrencePercent;
}
