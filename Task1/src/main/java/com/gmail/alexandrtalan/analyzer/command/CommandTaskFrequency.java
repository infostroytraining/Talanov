package com.gmail.alexandrtalan.analyzer.command;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandTaskFrequency implements Command {

    @Override
    public void execute(List<String> words) {
        long start = System.currentTimeMillis();

        Map<String, Integer> wordAndFrequency = new LinkedHashMap<>();
        words.forEach(word -> {
            if (wordAndFrequency.containsKey(word)) {
                Integer currentCount = wordAndFrequency.get(word);
                wordAndFrequency.replace(word, currentCount + 1);
            } else {
                wordAndFrequency.put(word, 1);
            }
        });

        wordAndFrequency.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(3)
                .sorted((o1, o2) -> o2.getKey().compareTo(o1.getKey()))
                .forEach(o -> System.out.println(o.getKey() + " ==> " + o.getValue()));

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " millis");
    }
}
