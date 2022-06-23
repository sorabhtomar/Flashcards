package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static flashcards.utils.Logging.getUserInput;
import static flashcards.utils.Logging.printOutput;

public class Import implements Action {
    private Deck deck;

    public Import() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        printOutput("File name:");
        String filePath = getUserInput();

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

                int mistakes = 0;
                if (card.length > 2) {
                    mistakes = Integer.parseInt(card[2]);
                }

                deck.addOrUpdateCard(term, definition, mistakes);
            }
            printOutput(String.format("%d cards have been loaded.", count));

        } catch (FileNotFoundException e) {
            printOutput("File not found.");
        }
    }
}
