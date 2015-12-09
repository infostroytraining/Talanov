package com.gmail.alexandrtalan.analyzer.command;

import java.util.List;

public interface Command {
    void execute(List<String> words);
}
