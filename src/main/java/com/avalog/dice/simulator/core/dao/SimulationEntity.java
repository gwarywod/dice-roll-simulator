package com.avalog.dice.simulator.core.dao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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

    @OneToOne(mappedBy = "simulation", cascade = CascadeType.ALL)
    protected SimulationResultEntity resultEntity;
}

