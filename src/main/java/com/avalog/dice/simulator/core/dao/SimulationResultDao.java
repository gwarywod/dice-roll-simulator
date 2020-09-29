package com.avalog.dice.simulator.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationResultDao extends JpaRepository<SimulationResultEntity, Long>, JpaSpecificationExecutor<SimulationResultEntity> {

}
