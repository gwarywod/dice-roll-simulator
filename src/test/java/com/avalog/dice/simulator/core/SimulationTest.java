package com.avalog.dice.simulator.core;

import com.avalog.dice.simulator.core.model.RollResult;
import com.avalog.dice.simulator.core.model.SimulationResult;
import com.avalog.dice.simulator.core.service.SimulationService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

class SimulationTest {

    private DiceRollSimulator diceRollSimulator;

    private DiceRollManager diceRollManager;

    private SimulationService simulationService;

    @BeforeEach
    void initTest() {
        diceRollManager = Mockito.mock(DiceRollManager.class);
        simulationService = Mockito.mock(SimulationService.class);
        diceRollSimulator = new DiceRollSimulator(diceRollManager, simulationService);
    }

    @Test
    public void checkResultOfSimulation() {

        final int pieces = 3;
        final int diceSides = 6;
        final int roles = 10;
        final int rollSum = 6;

        Mockito.when(diceRollManager.sumRoll(any(), pieces)).thenReturn(rollSum);

        SimulationConfig simulationConfig = SimulationConfig.builder().dicePieces(pieces).diceSides(diceSides).rolls(roles).build();

        SimulationResult simulateResult = diceRollSimulator.simulate(simulationConfig);

        Assertions.assertNotNull(simulateResult, "Result of simulation must be non-null");
        Assertions.assertNotNull(simulateResult.getRollResults(), "Simulation steps must be non-null");
        Assertions.assertEquals(roles, simulateResult.getRollResults().size(), "Wrong number of result steps");

        List<RollResult> expectedStepsResult = IntStream.rangeClosed(1, roles).mapToObj(i -> RollResult.builder().sum(rollSum).build())
            .collect(Collectors.toList());
        Assertions.assertIterableEquals(expectedStepsResult, simulateResult.getRollResults(), "Unexpected step of result");

        Mockito.verify(diceRollManager, Mockito.times(roles)).sumRoll(any(), pieces);

    }
}