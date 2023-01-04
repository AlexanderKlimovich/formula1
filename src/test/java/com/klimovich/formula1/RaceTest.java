package com.klimovich.formula1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RaceTest {
    private Race race = new Race();

    @Test
    void makeRaceResults_ShouldThrowException_WhenNoFileInDirectory() {
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            race.makeRaceResults("file.txt", "file.log", "file2.log");
        });
    }

    @Test
    void makeRaceResults_ShouldThrowException_WhenTimeDataIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            race.makeRaceResults("abbreviations.txt", "start.log", "endIncorect.log");
        });
    }

    @Test
    void makeRaceResults_ShouldThrowException_WhenInputDataIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            race.makeRaceResults(null, null, null);
        });
    }

    @Test
    void makeRaceResults_ShouldThrowException_WhenAbbreviationsDataIncorect() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            race.makeRaceResults("abbreviationsIncorect.txt", "start.log", "end.log");
        });
    }

    @Test
    void makeRaceResults_ShouldReturnNotEmptyList_WhenFilesIsEmpty() throws IOException, URISyntaxException {
        List<RacerResult> result = race.makeRaceResults("abbreviationsEmpty.txt", "startEmpty.log", "endEmpty.log");
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void makeRaceResults_ShouldReturnNotEmptyList_WhenFilesConsistInformation() throws IOException, URISyntaxException {
        RacerResult expected = new RacerResult("Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S"));
        List<RacerResult> result = race.makeRaceResults("fileWithOneString.txt", "start.log", "end.log");
        Assertions.assertEquals(expected, result.get(0));
    }

}
