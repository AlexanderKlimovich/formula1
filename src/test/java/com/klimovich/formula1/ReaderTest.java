package com.klimovich.formula1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.NoSuchFileException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReaderTest {
    private Reader reader = new Reader();

    @Test
    void read_ShouldThrowException_WhenPathToStartFileIsNull() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            reader.read(null);
        });
        Assertions.assertEquals("Path to the file can't be null", exception.getMessage());
    }

    @Test
    void read_ShouldThrowException_WhenNoFileInDirectory() {
        NoSuchFileException exception = Assertions.assertThrows(NoSuchFileException.class, () -> {
            reader.read("abbreviationsn.txt");
        });
        Assertions.assertEquals("File abbreviationsn.txt not found", exception.getMessage());
    }

    @Test
    void read_ShouldReturnStreamOfStrings_WhenFileIsNotEmpty() throws IOException, URISyntaxException {
        Stream<String> expected = Stream.of("SVF_Sebastian Vettel_FERRARI");
        Assertions.assertEquals(expected.collect(Collectors.toList()), reader.read("fileWithOneString.txt").collect(
                Collectors.toList()));
    }

}
