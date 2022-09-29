package FTSLocalSystem;

import java.util.Scanner;

public class MainMenu {

    KnowledgeBase knowledgeBase;
    Publisher publisher = new Publisher();
    Scanner scanner;

    public MainMenu(KnowledgeBase knowledgeBase, Scanner scanner) {
        this.knowledgeBase = knowledgeBase;
        this.scanner = scanner;
    }

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

    private void printMainMenu() {
        System.out.println("Was wollen Sie tun?");
        System.out.println("TASTE\tAKTION");
        System.out.println("[1]\t\tFTS-Update veröffentlichen");
        System.out.println("[2]\t\tWissen anschauen");
        System.out.println("[3]\t\tApplikation beenden");
    }
}
