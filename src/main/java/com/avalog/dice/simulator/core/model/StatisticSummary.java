package com.avalog.dice.simulator.core.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder
@Getter
public class StatisticSummary {
    private Integer diceSides;
    private Integer diceNumber;
    private Integer totalSimulations;
    private Long totalRolls;

    @Singular
    private List<StatisticDistribution> statisticDistributions;
}
