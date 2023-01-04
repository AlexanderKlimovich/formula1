package com.klimovich.formula1;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RacerParser implements Parser<Racer> {
    private final String REGEX = "^[A-Z]{3}\\_[A-Z a-z]+\\_[A-Z a-z]+$";
    private Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public Racer parse(String input) {
        Optional.ofNullable(input).orElseThrow(() -> new IllegalArgumentException(
                "Input string can't be null"));
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input string does not match required format");
        }
        String[] data = input.split("_");
        return new Racer(data[0], data[1], data[2]);
    }
}
