package com.avalog.dice.simulator.core.utils;

import com.avalog.dice.simulator.core.model.Dice;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DiceHelper {

    public static List<Dice> prepareDices(int sides, int pieces) {
        return IntStream.rangeClosed(1, pieces).mapToObj(o -> Dice.of(sides)).collect(Collectors.toList());
    }
}
