package flashcards;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        System.out.println("Input the number of cards: ");
        int n = Integer.parseInt(scn.nextLine()); // or, Integer.valueOf(scn.nextLine());

        // Issues are created when inputting String after non-string inputs
        // int n = scn.nextInt();
        // scn.nextLine();

        Map<String, String> termDefinition = new LinkedHashMap<>();
        Map<String, String> definitionTerm = new LinkedHashMap<>();

        for (int i = 1; i <= n; i++) {
            System.out.printf("Card #%d:%n", i);
            String term = null;

            // input: represents the desired user input
            boolean input = false;
            do {
                term = scn.nextLine();
                if (termDefinition.containsKey(term)) {
                    System.out.printf("The term \"%s\" already exists. Try again:%n", term);
                } else {
                    input = true;
                }
            } while (!input);


            System.out.printf("The definition for card #%d:%n", i);
            String definition = null;

            input = false;
            do {
                definition = scn.nextLine();
                if (definitionTerm.containsKey(definition)) {
                    System.out.printf("The definition \"%s\" already exists. Try again:%n", definition);
                } else {
                    input = true;
                }
            } while (!input);

            termDefinition.put(term, definition);
            definitionTerm.put(definition, term);
        }

        // Asking the user for answers to various terms in the flashcards
        for (Map.Entry<String, String> entry : termDefinition.entrySet()) {
            System.out.printf("Print the definition of \"%s\":%n", entry.getKey());
            String answer = scn.nextLine();

             if (entry.getValue().equals(answer)) {
                 System.out.println("Correct!");
             } else {
                 if (definitionTerm.containsKey(answer)) {
                     System.out.printf("Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".%n", entry.getValue(), definitionTerm.get(answer));
                 } else {
                    System.out.printf("Wrong. The right answer is \"%s\".%n", entry.getValue());
                 }
             }
        }
    }
}
