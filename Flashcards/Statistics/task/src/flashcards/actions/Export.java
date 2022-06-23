package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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

        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            printWriter.println("term|definition|mistakes");

            for (Map.Entry<String, String> card: deck.getCards().entrySet()) {
                int mistakes = deck.getCardMistakes().get(card.getKey());
                printWriter.printf("%s|%s|%s%n", card.getKey(), card.getValue(), mistakes);
            }
        } catch (IOException e) {
            printOutput("Unable to write to file: " + fileName);
        }

        printOutput(String.format("%d cards have been saved.", deck.size()));
    }
}
