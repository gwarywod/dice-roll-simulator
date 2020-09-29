package com.avalog.dice.simulator.core;

import com.avalog.dice.simulator.core.dao.SimulationEntity;
import com.avalog.dice.simulator.core.model.Dice;
import com.avalog.dice.simulator.core.model.RollResult;
import com.avalog.dice.simulator.core.model.SimulationResult;
import com.avalog.dice.simulator.core.service.SimulationService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DiceRollSimulator {

    private final DiceRollManager diceRollManager;

    private final SimulationService simulationService;

    public DiceRollSimulator(DiceRollManager diceRollManager, SimulationService simulationService) {
        this.diceRollManager = diceRollManager;
        this.simulationService = simulationService;
    }

    /**
     * Entry point of dice roll simulation based on configuration.
     * @param configuration
     * @return the result of simulation
     */
    public SimulationResult simulate(SimulationConfig configuration) {

        log.info("Starting simulation based on {}", configuration);

        SimulationEntity simulation = simulationService.createSimulation(configuration);

        Dice dice = Dice.of(configuration.getDiceSides());

        List<RollResult> results = IntStream.rangeClosed(1, configuration.getRolls())
            .mapToObj(i -> rollDices(dice, configuration.getDicePieces()))
            .collect(Collectors.toList());

        simulationService.addResults(results, simulation);

        return SimulationResult.builder().rollResults(results).build();
    }

    private RollResult rollDices(Dice dice, int pieces) {
        int sum = diceRollManager.sumRoll(dice, pieces);
        return RollResult.builder().sum(sum).build();
    }
}
