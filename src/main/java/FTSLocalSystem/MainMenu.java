package FTSLocalSystem;

import java.util.Scanner;

/**
 * This class provides all necessary methods to display the main menu and react accordingly on user input.
 */
public class MainMenu {

    KnowledgeBase knowledgeBase;
    Publisher publisher = new Publisher();
    Scanner scanner;

    /**
     * This constructor initializes variables.
     * @param knowledgeBase
     * @param scanner
     */
    public MainMenu(KnowledgeBase knowledgeBase, Scanner scanner) {
        this.knowledgeBase = knowledgeBase;
        this.scanner = scanner;
    }

    /**
     * This method displays the main menu, providing 3 Options to the user:
     * 1. Publish an XML Document to the specified broker
     * 2. Enter the knowledge base
     * 3. Exit the application
     * @param brokerStub reference to remote broker object
     */
    public void inputRoutine(IBroker brokerStub) {
        while(true) {
            this.printMainMenu();
            String intention = scanner.nextLine();
            if (intention.equals("1")) {
                // Enter publishing process
                publisher.publishRoutine(brokerStub, scanner);
            } else if(intention.equals("2")) {
                //Enter Knowledge Base
                knowledgeBase.browseKnowledgeBase();
            } else if(intention.equals("3")) {
                System.exit(0);
            } else {
                System.out.println("Tut mir leid, das habe ich nicht verstanden!");
            }
        }
    }

    /**
     * This method prints the main menu to the console.
     */
    private void printMainMenu() {
        System.out.println("Was wollen Sie tun?");
        System.out.println("TASTE\tAKTION");
        System.out.println("[1]\t\tFTS-Update veröffentlichen");
        System.out.println("[2]\t\tWissen anschauen");
        System.out.println("[3]\t\tApplikation beenden");
    }
}
