package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import static flashcards.utils.Logging.printOutput;

public class ResetStats implements Action {
    private Deck deck;

    public ResetStats() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        deck.resetCardStats();
        printOutput("Card statistics have been reset.");
    }
}
