package com.gmail.alexandrtalan.analyzer.command;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CommandTaskDuplicates implements Command {

    @Override
    public void execute(List<String> words) {
        long start = System.currentTimeMillis();

        final int DUPLICATES_COUNT = 3;
        Set<String> duplicates = new LinkedHashSet<>(DUPLICATES_COUNT);
        out:
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                if (words.get(i).equals(words.get(j))) {
                    duplicates.add(new StringBuilder(words.get(i)).reverse().toString().toUpperCase());
                    if (duplicates.size() == DUPLICATES_COUNT) break out;
                }
            }
        }

        duplicates.forEach(System.out::println);
        System.out.println("Elapsed time: " + (System.currentTimeMillis() - start) + " millis");
    }
}
