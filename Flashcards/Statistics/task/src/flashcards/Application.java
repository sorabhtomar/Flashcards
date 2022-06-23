package flashcards;

import flashcards.actions.Action;
import flashcards.actions.ActionFactory;
import flashcards.entities.Deck;

import static flashcards.utils.Logging.getUserInput;
import static flashcards.utils.Logging.printOutput;

public class Application {
    public static Application app;

    private String[] args;
    private Deck deck;

    public Application(String[] args) {
        this.args = args;
        deck = new Deck();

        app = this;
    }

    public Deck getDeck() {
        return deck;
    }

    public void run() {
        boolean exit = false;
        do {

            printOutput("Input the action (add, remove, import, export, ask, exit):");
            String input = getUserInput();

            if ("exit".equals(input)) {
                exit = true;
                printOutput("Bye bye!");
            } else {
                ActionFactory factory = new ActionFactory();

                Action action = factory.create(input);
                action.run();

                printOutput("");
            }

        } while (!exit);
    }
}
