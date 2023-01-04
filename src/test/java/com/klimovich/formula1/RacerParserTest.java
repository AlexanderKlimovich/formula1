package com.klimovich.formula1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RacerParserTest {
    private RacerParser parser = new RacerParser();

    @Test
    void parse_ShouldThrowException_WhenInputDataIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(null);
        });
        Assertions.assertEquals("Input string can't be null", exception.getMessage());
    }

    @Test
    void parse_ShouldThrowException_WhenInputSringNotMachRegex() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("LH_Lewis Hamilton_");
        });
        Assertions.assertEquals("Input string does not match required format", exception.getMessage());
    }

    @Test
    void parse_ShouldReturnRacerObject_WhenInputSringMachRegex() {
        Racer expected = new Racer("KRF", "Kimi Raikkonen", "FERRARI");
        Assertions.assertEquals(expected, parser.parse("KRF_Kimi Raikkonen_FERRARI"));
    }
}
