package com.klimovich.formula1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimeInfoParserTest {
    private TimeInfoParser parser = new TimeInfoParser();

    @Test
    void parse_ShouldThrowException_WhenInputDataIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse(null);
        });
        Assertions.assertEquals("Input string can't be null", exception.getMessage());
    }

    @Test
    void parse_ShouldThrowException_WhenInputSringNotMachRequiredFormat() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            parser.parse("SS2018-05-24_12:02:58");
        });
        Assertions.assertEquals("Input string does not match required format", exception.getMessage());
    }

    @Test
    void parse_ShouldThrowException_WhenInputTimeDataNotMachRequiredFormat() {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            parser.parse("SAS208-05-24_12:02");
        });
    }

    @Test
    void parse_ShouldReturnTimeInfoObject_WhenInputStringMachRegex() {
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        TimeInfo expected = new TimeInfo("SVF", LocalDateTime.parse("2018-05-24_12:02:58.917", FORMATTER));
        Assertions.assertEquals(expected, parser.parse("SVF2018-05-24_12:02:58.917"));
    }
}
