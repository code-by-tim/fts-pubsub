package FTSLocalSystem;

import java.util.LinkedList;
import java.util.Scanner;

public class KnowledgeBase {

    LinkedList<String> knowledgeBase;
    Scanner scanner;

    public KnowledgeBase(Scanner scanner) {
        this.knowledgeBase = new LinkedList<String>();
        this.scanner = scanner;
    }

    public void browseKnowledgeBase() {
        System.out.println("Browsing Knowledge Base...");
        System.out.println("There was/were " + knowledgeBase.size() + " received MTS-Knowledge Update(s). Which one would you like to see?");
        System.out.println("(Enter a number between 1 and " + knowledgeBase.size() + ")");
        String input = scanner.nextLine();
        try {
            int enteredNumber = Integer.parseInt(input);
            if (1 <= enteredNumber && enteredNumber <= knowledgeBase.size()) { //check if the number is valid
                System.out.println("This was update Nr. " + enteredNumber + ":");
                System.out.println(knowledgeBase.get(enteredNumber - 1));
            } else {
                System.out.println("The number was not in the valid range. Please try again!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Only Numbers can be accepted. Please try again!");
        }
    }

    public void mergeWithKnowledgeBase(String update) {
        knowledgeBase.add(update);
        System.out.println("KnowledgeBase: Updated saved to Knowledge Base!");
    }

}
