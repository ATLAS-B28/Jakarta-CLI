package org.example.cli.Commands;

import org.example.cli.Command;
import org.example.cli.CommandManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CdCommand implements Command {

    private CommandManager commandManager;

    public CdCommand(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void execute(String[] args) {
        try {
            if(args.length < 2) {
                System.out.println("Usage: cd <directory>");
                return;
            }

            String pathStr = args[1];
            Path currDict = commandManager.getCurrDict();
            Path target = currDict.resolve(pathStr).normalize();

            if(!target.toFile().exists()) {
                System.out.println("Directory does not exist: " + pathStr);
                return;
            }

            if(!target.toFile().isDirectory()) {
                System.out.println("Not a directory: " + pathStr);
                return;
            }
            commandManager.setCurrDict(target);

        } catch (Exception e) {
            System.out.println("Error changing directory: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Change current directory";
    }

    @Override
    public String getName() {
        return "cd";
    }
}
