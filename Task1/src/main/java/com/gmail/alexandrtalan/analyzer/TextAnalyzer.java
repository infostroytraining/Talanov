package com.gmail.alexandrtalan.analyzer;

import com.beust.jcommander.JCommander;
import com.gmail.alexandrtalan.analyzer.command.Command;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class TextAnalyzer {

    public static void main(String[] args) throws IOException {

        args = new String[]{""};

        JCommanderParameters jCommanderParameters = new JCommanderParameters();
        new JCommander(jCommanderParameters, args);
        Command commandTask = jCommanderParameters.getTask();
        String filePath = jCommanderParameters.getPath();

        List<String> words = Arrays.asList(readFile(filePath).split("\\s+|\\.\\s+|\\.|,\\s+|,|\\?\\s+|\\?|!\\s+|!"));
        commandTask.execute(words);
    }

    public static String readFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), Charset.forName("UTF-8")))) {
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
                result.append(" ");
            }
            return result.toString();
        }
    }
}

