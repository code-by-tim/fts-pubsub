package FTSLocalSystem;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class manages the knowledge of the MTS-system.
 * In the prototypical version it provides methods to browse each received update and add new updates to the knowledge base.
 *
 */
public class KnowledgeBase {

    // As this is a prototype, the knowledge base is a List of all received knowledge.
    // In future versions that should be replaced by a more sophisticated system (database).
    LinkedList<String> knowledgeBase;
    Scanner scanner;

    public KnowledgeBase(Scanner scanner) {
        this.knowledgeBase = new LinkedList<String>();
        this.scanner = scanner;
    }

    /**
     * This method gives the user access to the Knowledge Base through the command line.
     * The prototype only allows to view the knowledge base.
     */
    public void browseKnowledgeBase() {
        System.out.println("Betrete Knowledge Base...");
        System.out.println("KnowledgeBase: Es wurde(n) " + knowledgeBase.size() + " Update(s) empfangen. Welches davon möchten Sie sehen?");
        System.out.println("KnowledgeBase: (Gebe eine Nummer zwischen 1 und " + knowledgeBase.size() + " ein)");
        String input = scanner.nextLine();
        try {
            int enteredNumber = Integer.parseInt(input);
            if (1 <= enteredNumber && enteredNumber <= knowledgeBase.size()) { //check if the number is valid
                System.out.println("Das war Update Nr. " + enteredNumber + ":");
                System.out.println(knowledgeBase.get(enteredNumber - 1));
            } else {
                System.out.println("KnowledgeBase: Die Nummer befindet sich nicht im gültigen Bereich. Bitte versuche es erneut!");
            }
        } catch (NumberFormatException e) {
            System.out.println("KnowledgeBase: Nur Zahlen werden akzeptiert. Bitte versuchen Sie es erneut!");
        }
    }

    /**
     * Adds the update to the knowledge base and informs the user about it.
     * In future updates this method could do the following:
     * It should compare the existing knowledge in the database with the update and extend or overwrite it
     * depending on if the elements in the received update are new or exist already.
     * @param update
     */
    public void mergeWithKnowledgeBase(String update) {
        knowledgeBase.add(update);
        System.out.println("KnowledgeBase: Update gespeichert!");
    }

}
