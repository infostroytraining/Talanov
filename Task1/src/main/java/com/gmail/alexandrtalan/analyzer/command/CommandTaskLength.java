package com.gmail.alexandrtalan.analyzer.command;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommandTaskLength implements Command {

    @Override
    public void execute(List<String> words) {
        long start = System.currentTimeMillis();

        Map<String, Integer> wordAndLength = new LinkedHashMap<>();
        words.forEach(word -> wordAndLength.put(word, word.length()));

        wordAndLength.entrySet()
                .stream()
                .sorted((o1, o2) -> o2.getValue() - o1.getValue())
                .limit(3)
                .forEach(o -> System.out.println(o.getKey() + " ==> " + o.getValue()));

        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " millis");
    }
}
