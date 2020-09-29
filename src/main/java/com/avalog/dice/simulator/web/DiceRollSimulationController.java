package com.avalog.dice.simulator.web;

import com.avalog.dice.simulator.core.DiceRollSimulator;
import com.avalog.dice.simulator.core.DiceRollStatisticProvider;
import com.avalog.dice.simulator.core.SimulationConfig;
import com.avalog.dice.simulator.core.model.RollResult;
import com.avalog.dice.simulator.core.model.SimulationResult;
import com.avalog.dice.simulator.core.model.Statistics;
import com.avalog.dice.simulator.web.api.DiceRollStatisticsResponse;
import com.avalog.dice.simulator.web.api.SimulationRequest;
import com.avalog.dice.simulator.web.api.SimulationResponse;
import com.avalog.dice.simulator.web.api.mapper.StatisticsMapper;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dice-roll")
public class DiceRollSimulationController {

    private DiceRollSimulator diceRollSimulator;

    private DiceRollStatisticProvider diceRollStatisticProvider;

    private StatisticsMapper statisticsMapper;

    public DiceRollSimulationController(DiceRollSimulator diceRollSimulator, DiceRollStatisticProvider diceRollStatisticProvider,
        StatisticsMapper statisticsMapper) {
        this.diceRollSimulator = diceRollSimulator;
        this.diceRollStatisticProvider = diceRollStatisticProvider;
        this.statisticsMapper = statisticsMapper;
    }

    @GetMapping
    @RequestMapping("/simulation")
    public SimulationResponse simulate(@Valid SimulationRequest simulationRequest) {

        SimulationConfig simulationConfig = SimulationConfig.builder()
            .diceSides(simulationRequest.getDiceSides()).dicePieces(simulationRequest.getDicePieces()).rolls(simulationRequest.getRolls()).build();

        SimulationResult rollResults = diceRollSimulator.simulate(simulationConfig);

        Map<Integer, Long> occurrenceFrequency = rollResults.getRollResults().stream()
            .map(RollResult::getSum)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return SimulationResponse.builder().occurrenceFrequency(occurrenceFrequency).build();
    }

    @GetMapping
    @RequestMapping("/statistics")
    public DiceRollStatisticsResponse statistic() {

        Statistics statistics = diceRollStatisticProvider.generateStatistics();

        return statisticsMapper.toDiceRollStatisticsResponse(statistics);
    }
}
