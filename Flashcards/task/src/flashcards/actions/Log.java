package flashcards.actions;

import flashcards.Application;
import flashcards.entities.Deck;
import flashcards.utils.Logging;

import java.io.IOException;
import java.io.PrintWriter;

import static flashcards.utils.Logging.getUserInput;
import static flashcards.utils.Logging.printOutput;

public class Log implements Action {
    private Deck deck;

    public Log() {
        deck = Application.app.getDeck();
    }

    @Override
    public void run() {
        printOutput("File name:");
        String fileName = getUserInput();

        try (PrintWriter writer = new PrintWriter(fileName)) {

            writer.print(Logging.getLog());
            printOutput("The log has been saved.");

        } catch (IOException e) {
            printOutput("Unable to write to file: " + fileName);
        }
    }
}
