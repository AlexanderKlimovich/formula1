package com.klimovich.formula1;

import java.util.stream.Stream;

public class Viev {
    public void showResult(Stream<String> raceResult) {
        raceResult.forEach(System.out::println);
    }

}
