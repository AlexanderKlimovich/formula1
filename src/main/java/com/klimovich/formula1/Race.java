package com.klimovich.formula1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Race {
    private Reader reader = new Reader();
    private RacerParser racerParcer = new RacerParser();
    private TimeInfoParser timeInfoParser = new TimeInfoParser();

    public List<RacerResult> makeRaceResults(String abbreviationsFile, String startFile, String endFile)
            throws IOException, URISyntaxException {
        Stream<Racer> racers = reader.read(abbreviationsFile).map(racerParcer::parse);
        Map<String, LocalDateTime> startTimes = reader.read(startFile).map(timeInfoParser::parse).collect(
                Collectors.toMap(TimeInfo::getAbbreviation, TimeInfo::getTime));
        Map<String, LocalDateTime> finishTimes = reader.read(endFile).map(timeInfoParser::parse).collect(
                Collectors.toMap(TimeInfo::getAbbreviation, TimeInfo::getTime));
        return racers.map(r -> new RacerResult(r.getName(), r.getTeam(), Duration.between(startTimes.get(r
                .getAbbreviation()), finishTimes.get(r.getAbbreviation())))).collect(Collectors.toList());

    }
}
