package com.klimovich.formula1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VievTest {
    PrintStream standardOut = System.out;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    Viev viev = new Viev();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void showResult_ShouldPrintToConsole_WhenInputDataNotNull() {
        List<String> inputData = new ArrayList<>();
        inputData.add("1.Sebastian Vettel|FERRARI                  |1:04.415");
        inputData.add("2.Daniel Ricciardo|RED BULL RACING TAG HEUER|1:12.013");
        inputData.add("-----------------------------------------------------");
        inputData.add("3.Valtteri Bottas |MERCEDES                 |1:12.434");
        String expected = new StringJoiner(System.lineSeparator())
                .add("1.Sebastian Vettel|FERRARI                  |1:04.415")
                .add("2.Daniel Ricciardo|RED BULL RACING TAG HEUER|1:12.013")
                .add("-----------------------------------------------------")
                .add("3.Valtteri Bottas |MERCEDES                 |1:12.434")
                .toString();
        viev.showResult(inputData.stream());
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void showResult_ShouldPrintEmptyString_WhenInputDataIsEmpty() {
        String expected = "";
        viev.showResult(Stream.of(""));
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

}
