package org.example;


import org.example.cli.CommandManager;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        Scanner scanner = new Scanner(System.in);

        while(true) {

            Path curtDict = commandManager.getCurrDict();
            String prompt = curtDict.toString() + "> ";
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if(input.isEmpty()) {
                continue;
            }

            commandManager.executeCommand(input);

            if(commandManager.shouldExit()) {
                break;
            }
        }
        scanner.close();
    }

}