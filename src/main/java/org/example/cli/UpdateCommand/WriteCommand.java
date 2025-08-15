package org.example.cli.UpdateCommand;


import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class WriteCommand implements Command {

    private final CommandManager commandManager;

    public WriteCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: write <filename> <contents> [contents2] [contents3] ... [contentsN]");
            return;
        }

        try {
            int fileNameIdx = args[0].equalsIgnoreCase("write") ? 1 : 0;
            if(args.length <= fileNameIdx) {
                System.out.println("Usage: write <filename> <contents> [contents2] [contents3] ... [contentsN]");
                return;
            }
            String fileName = args[fileNameIdx];
            String contents = args.length > fileNameIdx + 1 ? String.join(" ", Arrays.copyOfRange(args, fileNameIdx + 1, args.length)) : "";
            Path filePath = commandManager.getCurrDict().resolve(fileName).normalize();
            System.out.println("Writing to file: " + filePath);
            Files.writeString(filePath, contents + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch(IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Write contents to a file";
    }

    @Override
    public String getName() {
        return "write";
    }
}