package com.avalog.dice.simulator.web.api;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DiceRollStatistic {
    private Integer diceNumber;
    private Integer diceSides;
    private Integer totalSimulations;
    private Long totalRolls;
    private List<DiceRollOccurrence> numberOccurrences;
}
