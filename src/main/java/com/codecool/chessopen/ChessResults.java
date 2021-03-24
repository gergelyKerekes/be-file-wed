package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        List<String> competitors;

        List<String> lines = getValueLines(fileName);

        Map<String, Integer> competitorsMap = getCompetitorsMap(lines);

        competitors = getCompetitorsSortedList(competitorsMap);

        return competitors;
    }

    public static List<String> getCompetitorsSortedList(Map<String, Integer> competitorsMap) {
        LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();

        competitorsMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        List<String> ret = reverseSortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        return ret;
    }

    public static Map<String, Integer> getCompetitorsMap(List<String> lines) {
        Map<String, Integer> ret = lines.
                stream()
                .map(x -> x.split(","))
                .collect(Collectors.toMap(
                        x -> x[0],
                        x -> (Integer.parseInt(x[1])
                                + Integer.parseInt(x[2])
                                + Integer.parseInt(x[3])
                                + Integer.parseInt(x[4])
                                + Integer.parseInt(x[5]))
                ));
        return ret;
    }

    public static List<String> getValueLines(String file) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return lines;
    }
}