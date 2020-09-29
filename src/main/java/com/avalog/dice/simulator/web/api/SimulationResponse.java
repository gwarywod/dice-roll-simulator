package com.avalog.dice.simulator.web.api;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SimulationResponse {

    private Map<Integer, Long> occurrenceFrequency;
}
