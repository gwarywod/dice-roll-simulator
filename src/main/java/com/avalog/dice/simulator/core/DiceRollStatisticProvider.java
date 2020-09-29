package com.avalog.dice.simulator.core;

import com.avalog.dice.simulator.core.dao.SimulationEntity;
import com.avalog.dice.simulator.core.dao.SimulationResultEntity;
import com.avalog.dice.simulator.core.model.StatisticDistribution;
import com.avalog.dice.simulator.core.model.StatisticSummary;
import com.avalog.dice.simulator.core.model.Statistics;
import com.avalog.dice.simulator.core.service.SimulationService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DiceRollStatisticProvider {

    private SimulationService simulationService;

    public DiceRollStatisticProvider(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    public Statistics generateStatistics() {

        log.info("Generating statistics");

        StopWatch started = StopWatch.createStarted();

        Map<Pair<Integer, Integer>, List<SimulationEntity>> pairListMap = simulationService.getAllSimulations().stream()
            .collect(Collectors.groupingBy(e -> Pair.of(e.getDicePieces(), e.getDiceSides())));

        List<StatisticSummary> statisticSummaries = pairListMap.entrySet().stream()
            .map(es -> calculateStatisticSummary(es.getKey().getFirst(), es.getKey().getSecond(), es.getValue())
            ).collect(Collectors.toList());

        log.info("Statistics generated successfully in {} ms.", started.getTime(TimeUnit.MILLISECONDS));

        return Statistics.builder().statisticSummaries(statisticSummaries).build();
    }

    private StatisticSummary calculateStatisticSummary(int diceNumber, int diceSides, List<SimulationEntity> entities) {
        long totalRolls = entities.stream().mapToLong(SimulationEntity::getRolls).sum();
        return StatisticSummary.builder().diceNumber(diceNumber).diceSides(diceSides)
            .totalRolls(totalRolls)
            .totalSimulations(entities.size())
            .statisticDistributions(calculateDistribution(totalRolls, entities))
            .build();
    }

    private List<StatisticDistribution> calculateDistribution(long totalRolls, List<SimulationEntity> entities) {
        Map<Integer, Long> occurrences = entities.stream()
            .flatMap(e -> e.getResultEntity().stream())
            .collect(Collectors.groupingBy(SimulationResultEntity::getSum, Collectors.counting()));

        return occurrences.entrySet().stream()
            .map(e -> StatisticDistribution.builder()
                .occurrencePercent(calculateOccurrence(totalRolls, e.getValue()))
                .value(e.getKey()).build())
            .collect(Collectors.toList());
    }

    private BigDecimal calculateOccurrence(long totalRolls,  long occurrence) {
        return BigDecimal.valueOf(occurrence, 4).divide(BigDecimal.valueOf(totalRolls, 4), RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)
            .setScale(2, RoundingMode.HALF_EVEN));
    }
}
