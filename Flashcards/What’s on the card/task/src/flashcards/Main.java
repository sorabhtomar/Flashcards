package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        String term = scn.nextLine();
        String definition = scn.nextLine();

        String answer = scn.nextLine();

         if (definition.equals(answer)) {
             System.out.println("Your answer is right!");
         } else {
             System.out.println("Your answer is wrong!");
         }
    }
}
