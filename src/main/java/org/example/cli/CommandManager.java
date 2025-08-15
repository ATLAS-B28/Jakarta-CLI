package org.example.cli;

import org.example.cli.Commands.*;
import org.example.cli.CreateCommands.CreateDir;
import org.example.cli.CreateCommands.CreateFile;
import org.example.cli.DelCommand.DeleteCommand;
import org.example.cli.DelCommand.DeleteDirCommand;
import org.example.cli.NaviCommand.CdCommand;
import org.example.cli.NaviCommand.LsCommand;
import org.example.cli.NaviCommand.PwdCommand;
import org.example.cli.UpdateCommand.Append;
import org.example.cli.UpdateCommand.RenameCommand;
import org.example.cli.UpdateCommand.ReplaceCommand;
import org.example.cli.UpdateCommand.WriteCommand;

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
        commandMap.put("write", new WriteCommand(this));
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
