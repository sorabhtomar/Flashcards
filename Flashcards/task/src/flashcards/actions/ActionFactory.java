package flashcards.actions;

import static flashcards.utils.Logging.printOutput;

public class ActionFactory {
    public Action create(String action) {
        switch (action) {
            case "add":
                return new Add();

            case "remove":
                return new Remove();

            case "import":
                return new Import();

            case "export":
                return new Export();

            case "ask":
                return new Ask();

            case "log":
                return new Log();

            case "hardest card":
                return new ShowStats();

            case "reset stats":
                return new ResetStats();

            default:
                printOutput("Invalid action selected. Try again!");
                return null;
        }
    }
}
