package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;

import java.util.Map;
import java.util.Map.Entry;

import static flashcards.utils.Logging.printOutput;
import static flashcards.utils.Logging.getUserInput;

public class Ask implements Action {
    private Deck deck;

    public Ask() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        System.out.println("How many times to ask?");
        int n = Integer.parseInt(getUserInput());

        int count = 0;
        while (true) {
            // for (Entry<String, String> card: deck.getCards().entrySet()) {
            //     printOutput(String.format("Print the definition of \"%s\":", card.getKey()));
            //     String answer = getUserInput();
            //
            //     if (card.getValue().equals(answer)) {
            //         printOutput("Correct!");
            //     } else {
            //         if (deck.getReversedCards().containsKey(answer)) {
            //             printOutput(String.format("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".", card.getValue(), deck.getReversedCards().get(answer)));
            //         } else {
            //             printOutput(String.format("Wrong. The right answer is \"%s\".", card.getValue()));
            //         }
            //
            //         // In either case, need to increment the mistake by 1 for the flashcard with "term" as card.getKey()
            //         deck.updateCardMistakes(card.getKey());
            //     }
            //     count++;
            //
            //     if (count == n) {
            //         return;
            //     }
            // }

            String key = deck.getRandomCard();
            String definition = deck.getCards().get(key);

            printOutput(String.format("Print the definition of \"%s\":", key));
            String answer = getUserInput();

            if (definition.equals(answer)) {
                printOutput("Correct!");
            } else {
                if (deck.getReversedCards().containsKey(answer)) {
                    printOutput(String.format("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".", definition, deck.getReversedCards().get(answer)));
                } else {
                    printOutput(String.format("Wrong. The right answer is \"%s\".", definition));
                }

                // In either case, need to increment the mistake by 1 for the flashcard with "term" as card.getKey()
                deck.updateCardMistakes(key);
            }
            count++;

            if (count == n) {
                return;
            }

        }
    }
}
