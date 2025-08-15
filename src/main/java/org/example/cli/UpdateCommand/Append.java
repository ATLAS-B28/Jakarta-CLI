package org.example.cli.UpdateCommand;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Append implements Command {

    private final CommandManager commandManager;

    public Append(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {

        if(args.length < 3) {
            System.out.println("Usage: append <filename> <content>");
            return;
        }

        try {
            Path srcpath = commandManager.getCurrDict().resolve(args[1]).normalize();
            Path destPath = commandManager.getCurrDict().resolve(args[2]).normalize();

            if(!Files.exists(srcpath) || !Files.isRegularFile(srcpath)) {
                System.out.println("Source file does not exist: " + args[1]);
                return;
            }

            if(!Files.exists(destPath) || !Files.isRegularFile(destPath)) {
                System.out.println("Destination file does not exist: " + args[2]);
                return;
            }

            List<String> lines = Files.readAllLines(srcpath);
            Files.write(destPath, lines, StandardOpenOption.APPEND);
            System.out.println("File appended successfully: " + args[1]);
        } catch (IOException e) {
            System.out.println("Error appending file: " + e.getMessage());
        }

    }

    @Override
    public String getDescription() {
        return "Append content to a file";
    }

    @Override
    public String getName() {
        return "append";
    }
}
