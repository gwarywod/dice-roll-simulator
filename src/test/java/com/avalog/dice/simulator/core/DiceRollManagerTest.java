package com.avalog.dice.simulator.core;

import com.avalog.dice.simulator.core.model.Dice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class DiceRollManagerTest {

    private static DiceRollManager diceRollManager;

    @BeforeAll
    static void initTest() {
        diceRollManager = new DiceRollManager();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void checkRollDiceResultRange(int sides) {

        Dice dice = Dice.of(sides);

        int result = diceRollManager.roll(dice);

        Assertions.assertTrue(result > 0 && result <= sides);
    }

    @Test
    public void checkSumRollDiceResult() {

        final int sides = 1;
        final int pieces = 100;

        Dice dice = Dice.of(sides);

        int result = diceRollManager.sumRoll(dice, pieces);

        Assertions.assertTrue(result == pieces * sides);
    }
}
