package org.example.cli.Commands;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.File;
import java.nio.file.Path;

public class LsCommand implements Command {

    private final CommandManager commandManager;

    public LsCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            Path currDict = commandManager.getCurrDict();
            File[] files = currDict.toFile().listFiles();

            if(files != null) {
                for(File file : files) {
                    String type = file.isDirectory()? "d" : "-";
                    System.out.println(type + " " + file.getName());
                }
            }
        } catch (Exception e) {
            System.out.println("Error listing directory: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "List directory contents";
    }

    @Override
    public String getName() {
        return "ls";
    }
}
