package com.avalog.dice.simulator.core;

import com.avalog.dice.simulator.core.model.Dice;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Component
public class DiceRollManager {

    /**
     * Roll a dice
     *
     * @return result of roll
     */
    public int roll(Dice dice) {
        return RandomUtils.nextInt(1, dice.getSides() + 1);
    }

    /**
     * Roll all dices and sum of the results.
     *
     * @return Sum of the values of the dice rolled
     */
    public int sumRoll(Dice dice, int pieces) {
        return IntStream.rangeClosed(1, pieces).map(i -> roll(dice)).sum();
    }
}
