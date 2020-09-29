package com.avalog.dice.simulator.core;

import com.avalog.dice.simulator.core.dao.SimulationEntity;
import com.avalog.dice.simulator.core.model.Dice;
import com.avalog.dice.simulator.core.model.RollResult;
import com.avalog.dice.simulator.core.model.SimulationResult;
import com.avalog.dice.simulator.core.service.SimulationService;
import com.avalog.dice.simulator.core.utils.DiceHelper;
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

        List<Dice> dices = DiceHelper.prepareDices(configuration.getDiceSides(), configuration.getDicePieces());

        List<RollResult> results = IntStream.rangeClosed(1, configuration.getRolls()).mapToObj(i -> rollDices(dices)).collect(Collectors.toList());

        simulationService.addResults(results, simulation);

        return SimulationResult.builder().rollResults(results).build();
    }

    private RollResult rollDices(List<Dice> dices) {
        int sum = diceRollManager.sumRoll(dices);
        return RollResult.builder().sum(sum).build();
    }
}
