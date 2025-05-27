package org.example.cli.Commands;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DeleteDirCommand implements Command {

    private final CommandManager commandManager;

    public DeleteDirCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: deletedir <directory> [directory2] [directory3] ... [directoryN]");
            return;
        }

        boolean anyErrors = false;

        for(int i = 1; i < args.length; i++) {
            String dirName = args[i];
            Path dirPath = commandManager.getCurrDict().resolve(dirName);

            if(!Files.exists(dirPath)) {
                System.out.println("Directory does not exist: " + dirName);
                anyErrors = true;
                continue;
            }

            if(!Files.isDirectory(dirPath)) {
                System.out.println("Not a directory: " + dirName);
                anyErrors = true;
                continue;
            }

            try (Stream<Path> dirContents = Files.list(dirPath)) {
                if(dirContents.findAny().isPresent()) {
                    System.out.println("Error: Directory is not empty: " + dirName);
                    anyErrors = true;
                    continue;
                }

                Files.delete(dirPath);
                System.out.println("Directory deleted: " + dirName);

            } catch (IOException e) {
                System.out.println("Error deleting directory: " + e.getMessage());
            }
        }

        if(anyErrors) {
            System.out.println("Some directories could not be deleted.");
        }

    }

    @Override
    public String getDescription() {
        return "Delete one or more directories";
    }

    @Override
    public String getName() {
        return "deletedir";
    }
}
