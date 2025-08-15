package org.example;


import org.example.cli.CommandManager;

import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        Scanner scanner = new Scanner(System.in);
        final String ANSI_BOLD = "\033[1m";
        final String ANSI_CYAN = "\033[36m";
        final String ANSI_BLUE = "\033[34m";
        final String ANSI_RESET = "\u001B[0m";

// Welcome banner with colors
        System.out.println(ANSI_BOLD + ANSI_BLUE);
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║" + ANSI_CYAN + "Welcome to the Jakarta CLI!             " + ANSI_BLUE + "║");
        System.out.println("╚════════════════════════════════════════╝" + ANSI_RESET);
        System.out.println(ANSI_BOLD + "  Type 'help' to see available commands\n" + ANSI_RESET);

        while(true) {

            Path curtDict = commandManager.getCurrDict();
            String prompt = curtDict.toString() + "> ";
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if(input.isEmpty()) {
                continue;
            }
            
            // Handle input that starts with '>'
            if (input.startsWith(">")) {
                input = input.substring(1).trim();
                if (input.isEmpty()) {
                    continue;
                }
            }

            commandManager.executeCommand(input);

            if(commandManager.shouldExit()) {
                break;
            }
        }
        scanner.close();
    }

}