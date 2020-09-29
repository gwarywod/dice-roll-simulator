package com.avalog.dice.simulator.core.dao;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "simulation")
@Data
public class SimulationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    protected Long id;

    protected Integer dicePieces;

    protected Integer diceSides;

    protected Integer rolls;

    @OneToMany(mappedBy = "simulation", fetch = FetchType.LAZY)
    protected List<SimulationResultEntity> resultEntity;
}

