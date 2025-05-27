package org.example.cli.Commands;

import org.example.cli.Command;

public class ExitCommand implements Command {

    private boolean exit = false;
    @Override
    public void execute(String[] args) {
        exit = true;
    }

    @Override
    public String getDescription() {
        return "Exit the program";
    }

    @Override
    public String getName() {
        return "exit";
    }

    public boolean shouldExit() {
        return exit;
    }
}
