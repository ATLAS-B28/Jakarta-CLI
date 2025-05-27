package org.example.cli.Commands;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class RenameCommand implements Command {

    private final CommandManager commandManager;

    public RenameCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        if(args.length < 2) {
            System.out.println("Usage: rename <filename>");
            System.out.println("Prompted to enter new name");
            return;
        }

        Path srcPath = commandManager.getCurrDict().resolve(args[1]).normalize();
         try {
             if(!Files.exists(srcPath)) {
                 System.out.println("File does not exist: " + args[1]);
                 return;
             }

             System.out.println("Enter new name: ");
             Scanner scanner = new Scanner(System.in);
             String newName = scanner.nextLine().trim();

             if(newName.isEmpty()) {
                 System.out.println("New name cannot be empty");
                 return;
             }

             Path targetPath = commandManager.getCurrDict().resolve(newName).normalize();

             if(Files.exists(targetPath)) {
                 System.out.println("File already exists: " + newName);
                 return;
             }

             Files.move(srcPath, targetPath);
             System.out.println("File renamed: " + args[1] + " -> " + newName);
         } catch (IOException e) {
             System.out.println("Error renaming file: " + e.getMessage());
         }
    }

    @Override
    public String getDescription() {
        return "Rename a file or directory";
    }

    @Override
    public String getName() {
        return "rename";
    }
}
