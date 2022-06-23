package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import static flashcards.utils.Logging.getUserInput;
import static flashcards.utils.Logging.printOutput;

public class Remove implements Action {
    private Deck deck;

    public Remove() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        printOutput("Which card?");
        String term = getUserInput();

        if (!deck.getCards().containsKey(term)) {
            printOutput(String.format("Can't remove \"%s\": there is no such card.", term));
            return;
        }

        deck.removeCard(term);
        printOutput("The card has been removed.");
    }
}
