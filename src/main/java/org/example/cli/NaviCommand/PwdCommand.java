package org.example.cli.NaviCommand;

import org.example.cli.Command;

import java.nio.file.Paths;

public class PwdCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println(Paths.get(".").toAbsolutePath().normalize());
    }

    @Override
    public String getDescription() {
        return "Show current directory";
    }

    @Override
    public String getName() {
        return "pwd";
    }
}
