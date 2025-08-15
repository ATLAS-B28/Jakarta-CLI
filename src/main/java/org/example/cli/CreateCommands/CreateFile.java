package org.example.cli.CreateCommands;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CreateFile implements Command {

    private final CommandManager commandManager;
    private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(
            "txt", "java", "html", "css", "kt", "scala",
            "js", "md", "xml", "json", "csv", "exe",
            "bat", "sh", "py", "c", "cpp", "toml", "yaml"
    );

    public CreateFile(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    @Override
    public void execute(String[] args) {
        try {
            if(args.length < 2) {
                System.out.println("Usage: create <file> [file2] [file3]");
                return;
            }

            boolean anyErrors = false;

            for(int i = 1; i < args.length; i++) {
                String fileName = args[i];
                Path filePath = commandManager.getCurrDict().resolve(fileName);

                if(Files.exists(filePath)) {
                    System.out.println("Warning: file already exists");
                    anyErrors = true;
                    continue;
                }
                String extension = getExtension(fileName);

                if(!extension.isEmpty() && !ALLOWED_EXTENSIONS.contains(extension)) {
                    System.out.println("Error: Invalid file extension. Allowed extensions are: "
                            + String.join(", ", ALLOWED_EXTENSIONS));
                    anyErrors = true;
                    continue;
                }


                Files.createFile(filePath);
                System.out.println("File created: " + fileName);

            }

            if(anyErrors) {
                System.out.println("Operation failed.");
            }


        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Create a new file";
    }

    private String getExtension(String fileName) {
        int dotIdx = fileName.lastIndexOf('.');
        if(dotIdx == -1) {
            return "";
        }
        return fileName.substring(dotIdx + 1).toLowerCase();
    }

    @Override
    public String getName() {
        return "createfile";
    }
}
