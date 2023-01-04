package com.klimovich.formula1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class Reader {
    public Stream<String> read(String input) throws IOException, URISyntaxException {
        Optional.ofNullable(input).orElseThrow(() -> new IllegalArgumentException(
                "Path to the file can't be null"));
        Optional.ofNullable(getClass().getClassLoader().getResource(input)).orElseThrow(() -> new NoSuchFileException(
                String.format("File %s not found", input)));
        return Files.lines(Paths.get(getClass().getClassLoader().getResource(
                input).toURI()));
    }
}
