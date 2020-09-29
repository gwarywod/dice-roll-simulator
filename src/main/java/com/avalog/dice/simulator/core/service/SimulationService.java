package com.avalog.dice.simulator.core.service;

import com.avalog.dice.simulator.core.SimulationConfig;
import com.avalog.dice.simulator.core.dao.SimulationDao;
import com.avalog.dice.simulator.core.dao.SimulationEntity;
import com.avalog.dice.simulator.core.dao.SimulationResultDao;
import com.avalog.dice.simulator.core.dao.SimulationResultEntity;
import com.avalog.dice.simulator.core.model.RollResult;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private SimulationDao simulationDao;
    private SimulationResultDao simulationResultDao;

    public SimulationService(SimulationDao simulationDao, SimulationResultDao simulationResultDao) {
        this.simulationDao = simulationDao;
        this.simulationResultDao = simulationResultDao;
    }

    public SimulationEntity createSimulation(SimulationConfig config) {

        SimulationEntity simulationEntity = new SimulationEntity();
        simulationEntity.setDicePieces(config.getDicePieces());
        simulationEntity.setDiceSides(config.getDiceSides());
        simulationEntity.setRolls(config.getRolls());

        return simulationDao.saveAndFlush(simulationEntity);
    }

    public void addResults(List<RollResult> rollResults, SimulationEntity simulationEntity) {

        List<SimulationResultEntity> collect = rollResults.stream().map(rr -> {
            SimulationResultEntity simulationResultEntity = new SimulationResultEntity();
            simulationResultEntity.setSimulation(simulationEntity);
            simulationResultEntity.setSum(rr.getSum());
            return simulationResultEntity;
        }).collect(Collectors.toList());

        simulationResultDao.saveAll(collect);
    }

    public List<SimulationEntity> getAllSimulations() {
        return simulationDao.findAll();
    }
}
