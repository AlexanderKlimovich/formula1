package com.klimovich.formula1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeInfoParser implements Parser<TimeInfo> {
    private DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private final String REGEX = "^[A-Z]{3}.*$";
    private Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public TimeInfo parse(String input) {
        Optional.ofNullable(input).orElseThrow(() -> new IllegalArgumentException(
                "Input string can't be null"));
        Matcher matcher = PATTERN.matcher(input);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Input string does not match required format");
        }
        return new TimeInfo(input.substring(0, 3), LocalDateTime.parse(input.substring(3), FORMATTER));
    }
}
