package com.avalog.dice.simulator.web;

import com.avalog.dice.simulator.core.DiceRollSimulator;
import com.avalog.dice.simulator.core.SimulationConfig;
import com.avalog.dice.simulator.core.model.RollResult;
import com.avalog.dice.simulator.core.model.SimulationResult;
import com.avalog.dice.simulator.web.api.SimulationRequest;
import com.avalog.dice.simulator.web.api.SimulationResponse;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dice-roll/simulation")
public class DiceRollSimulationController {

    @Autowired
    private DiceRollSimulator diceRollSimulator;

    @GetMapping
    public SimulationResponse simulate(@Valid SimulationRequest simulationRequest) {

        SimulationConfig simulationConfig = SimulationConfig.builder()
            .diceSides(simulationRequest.getDiceSides()).dicePieces(simulationRequest.getDicePieces()).rolls(simulationRequest.getRolls()).build();

        SimulationResult rollResults = diceRollSimulator.simulate(simulationConfig);

        Map<Integer, Long> occurrenceFrequency = rollResults.getRollResults().stream()
            .map(RollResult::getSum)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return SimulationResponse.builder().occurrenceFrequency(occurrenceFrequency).build();
    }
}
