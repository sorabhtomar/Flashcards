package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Input the number of cards: ");
        int n = Integer.valueOf(scn.nextLine()); // or, Integer.parseInt(scn.nextLine());

        // Issues are created when inputting String after non-string inputs
        // int n = scn.nextInt();
        // scn.nextLine();

        FlashCard[] cards = new FlashCard[n];
        for (int i = 0; i < cards.length; i++) {
            System.out.printf("Card #%d:%n", (i + 1));
            String term = scn.nextLine();

            System.out.printf("The definition for card #%d:%n", (i + 1));
            String definition = scn.nextLine();

            cards[i] = new FlashCard(term, definition);
        }

        // Asking the user for answers
        for (FlashCard card : cards) {
            System.out.printf("Print the definition of \"%s\":%n", card.getTerm());
            String answer = scn.nextLine();

             if (card.getDefinition().equals(answer)) {
                 System.out.println("Correct!");
             } else {
                 System.out.printf("Wrong. The right answer is \"%s\".%n", card.getDefinition());
             }
        }
    }
}
