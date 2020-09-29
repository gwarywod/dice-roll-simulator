CREATE TABLE simulation
(
    id          BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    dice_pieces BIGINT NOT NULL,
    dice_sides  BIGINT NOT NULL,
    rolls       BIGINT NOT NULL
);

CREATE TABLE simulation_result
(
    id            BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    sum           BIGINT NOT NULL,
    simulation_id BIGINT NOT NULL,
    constraint simulation_simulation_result_fk foreign key (simulation_id) references simulation (id)
);