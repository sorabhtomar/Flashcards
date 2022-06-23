package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
    private static final Scanner scn = new Scanner(System.in);

    private static Map<String, String> cards = new HashMap<>();
    private static Map<String, String> reversedCards = new HashMap<>();

    public static void main(String[] args) {

        boolean exit = false;
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = scn.nextLine();

            switch (action) {
                case "add":
                    addCard();
                    break;

                case "remove":
                    removeCard();
                    break;

                case "import":
                    importCardsFromFile();
                    break;

                case "export":
                    exportCardsToFile();
                    break;

                case "ask":
                    ask();
                    break;

                case "exit":
                    exit = true;
                    System.out.println("Bye bye!");
                    break;

                default:
                    System.out.println("Invalid action selected. Try again!");
                    break;
            }
            System.out.println();
        } while (!exit);
    }

    private static void addCard() {
        System.out.println("The card:");
        String term = scn.nextLine();

        if (cards.containsKey(term)) {
            System.out.printf("The card \"%s\" already exists.%n", term);
            return;
        }

        System.out.println("The definition of the card:");
        String definition = scn.nextLine();

        if(reversedCards.containsKey(definition)) {
            System.out.printf("The definition \"%s\" already exists.%n", definition);
            return;
        }

        cards.put(term, definition);
        reversedCards.put(definition, term);
        System.out.printf("The pair (\"%s\":\"%s\") has been added.%n", term, definition);
    }

    private static void removeCard() {
        System.out.println("Which card?");
        String term = scn.nextLine();

        if (!cards.containsKey(term)) {
            System.out.printf("Can't remove \"%s\": there is no such card.%n", term);
            return;
        }

        String definition = cards.get(term);
        cards.remove(term);
        reversedCards.remove(definition);
        System.out.println("The card has been removed.");
    }

    private static void importCardsFromFile() {
        System.out.println("File name:");
        String filePath = scn.nextLine();

        int count = 0;
        try (Scanner scn = new Scanner(new File(filePath))) {
            // Skipping the first line
            scn.nextLine();
            while (scn.hasNextLine()) {
                count++;

                String[] card = scn.nextLine().split("\\|");

                if (card.length == 0) {
                    // reached the end of the file (i.e. blank line at the end)
                    return;
                }

                // Adding this new card to existing cards
                String term = card[0];
                String definition = card[1];

                if (cards.containsKey(term)) {
                    String oldDefinition = cards.get(term);

                    // "cards" will be updated with the new value automatically
                    // But for "reversedCards" we'll need to do this process manually.
                    // So, remove the entry for "oldDefinition" from "reversedCards"
                    reversedCards.remove(oldDefinition);
                }
                cards.put(term, definition);
                reversedCards.put(definition, term);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        System.out.printf("%d cards have been loaded.%n", count);
    }

    private static void exportCardsToFile() {
        System.out.println("File name:");
        String fileName = scn.nextLine();

        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.println("Term|Definition");

            for (Entry<String, String> card: cards.entrySet()) {
                printWriter.printf("%s|%s%n", card.getKey(), card.getValue());
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + fileName);
        }

        System.out.printf("%d cards have been saved.%n", cards.size());
    }

    private static void ask() {
        System.out.println("How many times to ask?");
        int n = Integer.parseInt(scn.nextLine());

        int count = 0;
        while (true) {
            for (Entry<String, String> card: cards.entrySet()) {
                System.out.printf("Print the definition of \"%s\":%n", card.getKey());
                String answer = scn.nextLine();

                if (card.getValue().equals(answer)) {
                    System.out.println("Correct!");
                } else {
                    if (reversedCards.containsKey(answer)) {
                        System.out.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".%n", card.getValue(), reversedCards.get(answer));
                    } else {
                        System.out.printf("Wrong. The right answer is \"%s\".%n", card.getValue());
                    }
                }
                count++;

                if (count == n) {
                    return;
                }
            }
        }
    }
}
