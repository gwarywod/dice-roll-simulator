package com.avalog.dice.simulator.web.api.mapper;

import com.avalog.dice.simulator.core.model.StatisticDistribution;
import com.avalog.dice.simulator.core.model.StatisticSummary;
import com.avalog.dice.simulator.core.model.Statistics;
import com.avalog.dice.simulator.web.api.DiceRollOccurrence;
import com.avalog.dice.simulator.web.api.DiceRollStatistic;
import com.avalog.dice.simulator.web.api.DiceRollStatisticsResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public abstract class StatisticsMapper {

    @Mapping(source = "statisticSummaries", target = "statistics", qualifiedByName = "apStatisticSummariesToStatistics")
    public abstract DiceRollStatisticsResponse toDiceRollStatisticsResponse(Statistics statistics);

    @Named("apStatisticSummariesToStatistics")
    protected List<DiceRollStatistic> mapStatisticSummariesToStatistics(List<StatisticSummary> summaries) {
        return summaries.stream().map(this::toDiceRollStatistic).collect(Collectors.toList());
    }

    @Mapping(source = "statisticDistributions", target = "numberOccurrences", qualifiedByName = "mapStatisticDistributionsToNumberOccurrence")
    public abstract DiceRollStatistic toDiceRollStatistic(StatisticSummary statisticSummary);


    @Named("mapStatisticDistributionsToNumberOccurrence")
    protected List<DiceRollOccurrence> mapStatisticKeysToStatistics(List<StatisticDistribution> statisticDistributions) {
        return statisticDistributions.stream().map(this::toDiceRollOccurrence).collect(Collectors.toList());
    }

    @Mapping(target = "occurrencePercent",
        expression = "java(java.lang.String.format(\"%s%%\", statisticDistribution.getOccurrencePercent().setScale(2, java.math.RoundingMode.HALF_UP)))")
    public abstract DiceRollOccurrence toDiceRollOccurrence(StatisticDistribution statisticDistribution);
}
