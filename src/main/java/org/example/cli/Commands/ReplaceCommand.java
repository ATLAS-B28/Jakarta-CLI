package org.example.cli.Commands;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ReplaceCommand implements Command {

    private final CommandManager commandManager;

    public ReplaceCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length != 3) {
            System.out.println("Usage: replace <source> <destination>");
            return;
        }
        Path srcPath = commandManager.getCurrDict().resolve(args[1]);
        Path destPath = commandManager.getCurrDict().resolve(args[2]);

        try {
            if(Files.isDirectory(destPath)) {
                Path fileName = srcPath.getFileName();
                if(fileName != null) {
                    destPath = destPath.resolve(fileName);
                }
            }

            if(!Files.exists(srcPath)) {
                System.out.println("Source file does not exist: " + args[1]);
                return;
            }

            if(Files.isDirectory(srcPath)) {
                System.out.println("Cannot operate on directory: " + args[1]);
                return;
            }

            if(Files.exists(destPath)) {
                System.out.println("Destination file already exists: " + args[2]);
                String res = new java.util.Scanner(System.in).nextLine().trim().toLowerCase();
                if(!res.equalsIgnoreCase("Y") && !res.equalsIgnoreCase("y")) {
                    System.out.println("Replace operation cancelled.");
                    return;
                }
            }

            Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File content replaced: " + args[1] + " -> " + args[2]);
        } catch (IOException e) {
            System.out.println("Error replacing file content: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "replace a file";
    }

    @Override
    public String getName() {
        return "replace";
    }
}
