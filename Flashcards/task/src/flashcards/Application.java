package flashcards;

import flashcards.actions.Action;
import flashcards.actions.ActionFactory;
import flashcards.actions.Export;
import flashcards.actions.Import;
import flashcards.entities.Deck;

import static flashcards.utils.Logging.getUserInput;
import static flashcards.utils.Logging.printOutput;

public class Application {
    public static Application app;

    private String[] args;
    private Deck deck;

    // "in": holds the value of command line argument/flag/option "-import" (if passed)
    private String in;
    // out: holds the value of command line argument/flag/option "-export" (if passed)
    private String out;

    public Application(String[] args) {
        this.args = args;
        deck = new Deck();

        this.in = "";
        this.out = "";

        app = this;
    }

    public Deck getDeck() {
        return deck;
    }

    private void processRunArguments() {
        int position = 0;
        while (position < args.length) {
            // option: command line option/flag. Eg: -import, -export
            String option = args[position];

            switch (option) {
                case "-import":
                    in = args[position + 1];
                    position += 2;
                    break;

                case "-export":
                    out = args[position + 1];
                    position += 2;
                    break;
            }
        }
    }

    public void run() {
        processRunArguments();

        if (!"".equals(in)) {
            new Import().importCards(in);
            printOutput("");
        }

        boolean exit = false;
        do {

            printOutput("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String input = getUserInput();

            if ("exit".equals(input)) {
                exit = true;
                printOutput("Bye bye!");
            } else {
                ActionFactory factory = new ActionFactory();

                Action action = factory.create(input);
                action.run();

            }
            printOutput("");

        } while (!exit);

        if (!"".equals(out)) {
            new Export().exportCards(out);
        }
    }
}
