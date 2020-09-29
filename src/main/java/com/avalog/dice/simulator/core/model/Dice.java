package com.avalog.dice.simulator.core.model;

import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class Dice {

    public int sides;
}
