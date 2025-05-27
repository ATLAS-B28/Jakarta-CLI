package org.example.cli.Commands;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateDir implements Command {

    private final CommandManager commandManager;

    public CreateDir(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            if(args.length < 2) {
                System.out.println("Usage: create <directory> [dir1] [dir2] ...");
                return;
            }

            boolean anyErrors = false;

            for(int i = 1; i < args.length; i++) {
                String dirName = args[i];
                Path dirPath = commandManager.getCurrDict().resolve(dirName);

                if(Files.exists(dirPath)) {
                    System.out.println("Warning: Directory already exists: " + dirName);
                    anyErrors = true;
                    continue;
                }
                Files.createDirectory(dirPath);
                System.out.println("Directory created: " + dirName);
            }

            if(anyErrors) {
                System.out.println("Some directories could not be created.");
            }
        } catch(IOException e) {
            System.out.println("Error creating directory: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public String getName() {
        return "";
    }
}
