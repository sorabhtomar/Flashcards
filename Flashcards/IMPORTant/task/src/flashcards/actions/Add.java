package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import static flashcards.utils.Logging.getUserInput;
import static flashcards.utils.Logging.printOutput;

public class Add implements Action{
    private Deck deck;

    public Add() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        printOutput("The card:");
        String term = getUserInput();

        if (deck.getCards().containsKey(term)) {
            printOutput(String.format("The card \"%s\" already exists.", term));
            return;
        }

        printOutput("The definition of the card:");
        String definition = getUserInput();

        if(deck.getReversedCards().containsKey(definition)) {
            printOutput(String.format("The definition \"%s\" already exists.", definition));
            return;
        }

        deck.addCard(term, definition, 0);
        printOutput(String.format("The pair (\"%s\":\"%s\") has been added.", term, definition));
    }
}
