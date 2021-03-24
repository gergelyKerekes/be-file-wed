package com.codecool.chessopen;

import cccr.CCCRTestExecutionListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({CCCRTestExecutionListener.class})
public class ChessResultsTest {

    ChessResults chessResults;

    @BeforeEach
    void init() {
        chessResults = new ChessResults();
    }

    @Test
    void getCompetitorsNamesFromFileTest(){
        List<String> expected = Arrays.asList(
                "Lilla Carver",
                "Andrew Count",
                "Adam Field",
                "Adam Soon",
                "Evelin Shepherd",
                "George Tailor",
                "Leslie Wolf",
                "Alexander Woolly",
                "Martin Tailor",
                "Leslie Little");
        List<String> results = chessResults.getCompetitorsNamesFromFile("src/main/resources/results.txt");
        assertEquals(expected,results);
    }

    @Test
    void correctErrorMessageForFileNotFound(){
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        chessResults.getCompetitorsNamesFromFile("src/main/resources/notExisting.txt");
        assertEquals("File not found!",outputStreamCaptor.toString()
                .trim());
    }
}
