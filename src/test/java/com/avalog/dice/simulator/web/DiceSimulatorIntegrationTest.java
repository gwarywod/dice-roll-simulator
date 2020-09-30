package com.avalog.dice.simulator.web;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DiceSimulatorIntegrationTest {

    @Autowired
    private MockMvc mvc;

    private static Stream<Arguments> provideArgumentsToSimulateTest() {
        return Stream.of(
            Arguments.of(4, 6, 2),
            Arguments.of(4, 6, 2),
            Arguments.of(6, 5, 3),
            Arguments.of(6, 5, 3),
            Arguments.of(6, 5, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArgumentsToSimulateTest")
    public void simulateTest(int diceSides, int diceNumber, int rollNumber) throws Exception {

        mvc.perform(get("/api/dice-roll/simulation")
            .param("diceSides", Integer.toString(diceSides))
            .param("dicePieces", Integer.toString(diceNumber))
            .param("rolls", Integer.toString(rollNumber))
            .accept("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.occurrenceFrequency").exists());
    }
}
