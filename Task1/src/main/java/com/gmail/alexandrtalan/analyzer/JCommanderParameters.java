package com.gmail.alexandrtalan.analyzer;

import com.beust.jcommander.Parameter;
import com.gmail.alexandrtalan.analyzer.command.Command;

public class JCommanderParameters {

    @Parameter(names = {"-i", "--input"})
    private String path;

    @Parameter(names = {"-t", "--task"}, converter = CommandConverter.class)
    private Command task;

    public String getPath() {
        return path;
    }

    public Command getTask() {
        return task;
    }
}
