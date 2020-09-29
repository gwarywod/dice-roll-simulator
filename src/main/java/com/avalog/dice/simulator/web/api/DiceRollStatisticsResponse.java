package com.avalog.dice.simulator.web.api;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

@Builder
@Getter
public class DiceRollStatisticsResponse {

    @Singular
    private List<DiceRollStatistic> statistics;
}
