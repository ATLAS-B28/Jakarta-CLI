package org.example.cli;

public interface Command {
    void execute(String[] args);
    String getDescription();
    String getName();
}
