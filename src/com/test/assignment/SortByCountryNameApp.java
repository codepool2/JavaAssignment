package com.test.assignment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SortByCountryNameApp {


    private  String readInputFromFile()
            throws IOException {
        InputStream inputStream = SortByCountryNameApp.class.getResourceAsStream("test.txt");
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }

        return resultStringBuilder.toString();
    }

    private  HashMap<String, String> prepareData(String input) {

        final Matcher matcher = Pattern.compile("[\\d][\\d.]*,[A-Za-z]*").matcher(input);
        final List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group(0));
        }
      final  HashMap<String, String> result = new HashMap<>();
        for (String s : matches) {
            String[] s1 = s.split(",");
            if (s1.length == 2) {
                result.put(s1[0], s1[1]);
            }
        }

        return result;
    }

    public  Map<String, String> getSortedResult() throws IOException {

        HashMap<String, String> unsortedResult = prepareData(readInputFromFile());

      final  TreeMap<String, String> sortedResult = new TreeMap<>((s1, s2) -> {
            return unsortedResult.get(s1).compareTo(unsortedResult.get(s2));
        });
        sortedResult.putAll(unsortedResult);

        return sortedResult;

    }

}