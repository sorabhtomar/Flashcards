package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map.Entry;

import static flashcards.utils.Logging.getUserInput;
import static flashcards.utils.Logging.printOutput;

public class Export implements Action {
    private Deck deck;

    public Export() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        printOutput("File name:");
        String fileName = getUserInput();

        exportCards(fileName);
    }

    public void exportCards(String fileName) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            // printWriter.println("Term|Definition|Mistakes");

            for (Entry<String, String> card: deck.getCards().entrySet()) {
                int mistakes = deck.getCardMistakes().getOrDefault(card.getKey(), 0);
                printWriter.printf("%s|%s|%s%n", card.getKey(), card.getValue(), mistakes);
            }
        } catch (IOException e) {
            printOutput("Unable to write to file: " + fileName);
        }

        printOutput(String.format("%d cards have been saved.", deck.size()));
    }
}
