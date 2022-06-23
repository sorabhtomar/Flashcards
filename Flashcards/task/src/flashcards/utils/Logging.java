package flashcards.utils;

import java.util.Scanner;

public class Logging {
    private static Scanner scn;
    private static StringBuilder log;

    static {
        scn = new Scanner(System.in);
        log = new StringBuilder();
    }

    public static String getUserInput() {
        String input = scn.nextLine();
        log.append("> ").append(input).append("\n");

        return input;
    }

    public static void printOutput(String output) {
        log.append(output).append("\n");
        System.out.println(output);
    }

    public static StringBuilder getLog() {
        return log;
    }
}
