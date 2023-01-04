package com.klimovich.formula1;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Race race = new Race();
        Formatter formatter = new Formatter();
        Viev viev = new Viev();
        viev.showResult(formatter.makeStrings(race.makeRaceResults("abbreviations.txt", "start.log",
                "end.log"), 15));
    }
}
