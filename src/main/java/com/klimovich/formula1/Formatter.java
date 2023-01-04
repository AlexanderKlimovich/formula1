package com.klimovich.formula1;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class Formatter {

    public Stream<String> makeStrings(List<RacerResult> racerResults, int indexOfSeparator) {
        Optional.ofNullable(racerResults).orElseThrow(() -> new IllegalArgumentException(
                "Input List can't be null"));
        if (indexOfSeparator < 0) {
            throw new IllegalArgumentException("IndexOfSeparator can't be negative");
        }
        if (racerResults.isEmpty()) {
            return Stream.empty();
        }
        int maxNameLength = getMaxFieldLength(racerResults, RacerResult::getName);
        int maxTeamLength = getMaxFieldLength(racerResults, RacerResult::getTeam);
        int maxIndexLength = (int) (Math.log10(racerResults.size()) + 1);
        final String lineMask = "%-" + (maxNameLength + maxIndexLength) + "s" + "|" + "%-" + maxTeamLength + "s" + "|"
                + "%8s";
        AtomicInteger index = new AtomicInteger(1);
        return racerResults.stream().sorted(Comparator.comparing(RacerResult::getLapTime))
                .flatMap(racer -> makeString(lineMask, racer, index.getAndIncrement(), indexOfSeparator));
    }

    private Stream<String> makeString(String lineMask, RacerResult racerResult, int index, int indexOfSeparator) {
        String formatted = String.format(lineMask, (index + "." + racerResult.getName()), racerResult
                .getTeam(), formattingLapTime(racerResult.getLapTime()));
        if (index == indexOfSeparator) {
            return Stream.of(formatted, makeSeparator(formatted.length()));
        }
        return Stream.of(formatted);
    }

    private int getMaxFieldLength(List<RacerResult> racers, Function<RacerResult, String> getter) {
        return racers.stream().map(getter).mapToInt(String::length).max().getAsInt();
    }

    private String makeSeparator(int length) {
        return StringUtils.repeat("-", length);
    }

    private String formattingLapTime(Duration lapTime) {
        return DateTimeFormatter.ofPattern("m:ss.SSS").format(
                LocalTime.MIDNIGHT.plus(lapTime));
    }
}
