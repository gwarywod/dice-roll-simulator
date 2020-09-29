package com.avalog.dice.simulator.core.model;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.springframework.stereotype.Component;

@Getter
@Builder
public class SimulationResult {
    @Singular
    List<RollResult> rollResults;
}
