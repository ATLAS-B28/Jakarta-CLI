package org.example.cli.Commands;

import org.example.cli.Command;

public class HelpCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Available commands: ");
        System.out.println("help - show this message");
        System.out.println("exit - exit the program");
        System.out.println("pwd - show current directory");
        System.out.println("cd - Change directory");
        System.out.println("ls - List directory contents");
        System.out.println("createfile - Create a new file");
        System.out.println("createdir - Create a new directory");
        System.out.println("delete - Delete a file");
        System.out.println("deletedir - Delete a directory");
        System.out.println("replace - Replace a file contents");
        System.out.println("rename - Rename a file or directory");
        System.out.println("append - Append contents to a file");
    }

    @Override
    public String getDescription() {
        return "Show available commands";
    }

    @Override
    public String getName() {
        return "help";
    }
}
