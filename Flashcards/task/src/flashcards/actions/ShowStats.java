package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import static flashcards.utils.Logging.printOutput;

public class ShowStats implements Action {
    private Deck deck;

    public ShowStats() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        int maxMistakes = deck.getMaxMistakes();
        int maxMistakesCount = deck.getMaxMistakesTerms().size();

        if (maxMistakesCount == 0) {
            printOutput(String.format("There are no cards with errors."));

        } else if (maxMistakesCount == 1) {
            String term = deck.getMaxMistakesTerms().get(0);
            printOutput(String.format("The hardest card is \"%s\". You have %d %s answering it.", term, maxMistakes, (maxMistakes > 1 ? "errors" : "error")));

        } else if (maxMistakesCount > 1){
            StringBuilder terms = new StringBuilder();

            for (int i = 0; i < maxMistakesCount; i++) {
                String term = deck.getMaxMistakesTerms().get(i);
                terms.append("\"").append(term).append("\"");

                if (i < maxMistakesCount - 1) {
                    terms.append(", ");
                }
            }

            printOutput(String.format("The hardest cards are %s. You have %d %s answering them.", terms, maxMistakes, (maxMistakes > 1 ? "errors" : "error")));
        }
    }
}
