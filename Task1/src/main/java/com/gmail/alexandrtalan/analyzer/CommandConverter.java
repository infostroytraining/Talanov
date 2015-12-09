package com.gmail.alexandrtalan.analyzer;


import com.beust.jcommander.IStringConverter;
import com.gmail.alexandrtalan.analyzer.command.Command;
import com.gmail.alexandrtalan.analyzer.command.CommandTaskDuplicates;
import com.gmail.alexandrtalan.analyzer.command.CommandTaskFrequency;
import com.gmail.alexandrtalan.analyzer.command.CommandTaskLength;

public class CommandConverter implements IStringConverter<Command> {

    @Override
    public Command convert(String value) {
        switch (value) {
            case "frequency":
                return new CommandTaskFrequency();
            case "length":
                return new CommandTaskLength();
            case "duplicates":
                return new CommandTaskDuplicates();
            default:
                return null;
        }
    }
}
