package com.klimovich.formula1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FormatterTest {
    private Formatter formatter = new Formatter();

    @Test
    void makeStrings_ShouldThrowException_WhenArgsIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            formatter.makeStrings(null, 0);
        });
        Assertions.assertEquals("Input List can't be null", exception.getMessage());
    }

    @Test
    void makeStrings_ShouldThrowException_WhenIndexOfSeparatorIsNegative() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            formatter.makeStrings(new LinkedList<>(), -1);
        });
        Assertions.assertEquals("IndexOfSeparator can't be negative", exception.getMessage());
    }

    @Test
    void makeStrings_ShouldReturnEmptyString_WhenInputFilesIsEmpty() {
        List<RacerResult> list = new LinkedList<>();
        Assertions.assertTrue(formatter.makeStrings(list, 1).collect(Collectors.toList()).isEmpty());
    }

    @Test
    void makeStrings_ShouldReturnString_WhenInputDataValid() throws IOException, URISyntaxException {
        Race race = new Race();
        List<String> expected = new LinkedList<>();
        expected.add("1.Sebastian Vettel|FERRARI|1:04.415");
        List<String> result = formatter.makeStrings(race.makeRaceResults("fileWithOneString.txt", "start.log",
                "end.log"), 0).collect(Collectors.toList());
        Assertions.assertEquals(expected, result);
    }

    @Test
    void makeStrings_ShouldReturnStreamOfStringsWithDelimetr_WhenDataValid() throws IOException, URISyntaxException {
        Race race = new Race();
        List<String> expected = new LinkedList<>();
        expected.add("1.Sebastian Vettel|FERRARI                  |1:04.415");
        expected.add("-----------------------------------------------------");
        expected.add("2.Daniel Ricciardo|RED BULL RACING TAG HEUER|1:12.013");
        Assertions.assertEquals(expected, formatter.makeStrings(race.makeRaceResults("twoStrings.txt", "start.log",
                "end.log"), 1).collect(Collectors.toList()));
    }
}
