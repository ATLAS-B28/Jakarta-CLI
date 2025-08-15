package org.example.cli.DelCommand;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeleteCommand implements Command {

    private CommandManager commandManager;

    public DeleteCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: delete <file> [file2] [file3]");
            return;
        }

        boolean anyErrors = false;

        for(int i = 1; i < args.length; i++) {
            String fileName = args[1];
            Path filePath = commandManager.getCurrDict().resolve(fileName);

            if(!Files.exists(filePath)) {
                System.out.println("File does not exist: " + fileName);
                anyErrors = true;
                continue;
            }

            if(Files.isDirectory(filePath)) {
                System.out.println("Cannot delete directory: " + fileName);
                anyErrors = true;
                continue;
            }

            try {
                Files.delete(filePath);
                System.out.println("File deleted: " + fileName);
            } catch (IOException e) {
                System.out.println("Error deleting file: " + e.getMessage());
                anyErrors = true;
            }
        }

        if(anyErrors) {
            System.out.println("Some files could not be deleted.");
        }

    }

    @Override
    public String getDescription() {
        return "Delete one or more files";
    }

    @Override
    public String getName() {
        return "delete";
    }
}
