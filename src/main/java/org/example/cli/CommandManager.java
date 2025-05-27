package org.example.cli;

import org.example.cli.Commands.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private final Map<String, Command> commandMap = new HashMap<>();
    private Path currDict;

    public CommandManager() {
        currDict = Paths.get(".").toAbsolutePath().normalize();
        commandMap.put("help", new HelpCommand());
        commandMap.put("exit", new ExitCommand());
        commandMap.put("pwd", new PwdCommand());
        commandMap.put("cd", new CdCommand(this));
        commandMap.put("ls", new LsCommand(this));
        commandMap.put("createfile", new CreateFile(this));
        commandMap.put("createdir", new CreateDir(this));
        commandMap.put("delete", new DeleteCommand(this));
        commandMap.put("deletedir", new DeleteDirCommand(this));
        commandMap.put("replace", new ReplaceCommand(this));
        commandMap.put("rename", new RenameCommand(this));
        commandMap.put("append", new Append(this));
    }

    public void executeCommand(String input) {
        String[] args = input.split("\\s+");
        if(args.length == 0) {
            return;
        }

        String commandName = args[0].toLowerCase();
        Command command = commandMap.get(commandName);

        if(command != null) {
            command.execute(args);
        } else {
            System.out.println("Unknown command: " + commandName);
        }
    }

    public boolean shouldExit() {
        ExitCommand exitCommand = (ExitCommand) commandMap.get("exit");
        return exitCommand != null && exitCommand.shouldExit();
    }

    public Path getCurrDict() {
        return currDict;
    }

    public void setCurrDict(Path newDir) {
        currDict = newDir;
    }
}
