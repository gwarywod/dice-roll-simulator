package com.avalog.dice.simulator.core;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class SimulationConfig {

    /**
     * Indicates how many dice will be involved in simulation
     */
    private int dicePieces;

    /**
     * Indicates how many sides the dice will have
     */
    private int diceSides;

    /**
     * Indicates how many rolls will be in one simulation
     */
    private int rolls;
}
