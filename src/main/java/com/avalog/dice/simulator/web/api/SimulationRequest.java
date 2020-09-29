package com.avalog.dice.simulator.web.api;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SimulationRequest {

    /**
     * Indicates how many dice will be involved in simulation
     */
    @Min(value = 1)
    @Max(value = Integer.MAX_VALUE)
    @NotNull
    private Integer dicePieces;

    /**
     * Indicates how many sides the dice will have
     */
    @Min(value = 4)
    @Max(value = 6)
    @NotNull
    private Integer diceSides;

    /**
     * Indicates how many rolls will be in one simulation
     */
    @Min(value = 1)
    @NotNull
    @Max(value = Integer.MAX_VALUE)
    private Integer rolls;
}
