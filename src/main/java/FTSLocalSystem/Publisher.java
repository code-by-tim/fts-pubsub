package FTSLocalSystem;

import java.rmi.RemoteException;
import java.util.Scanner;

public class Publisher {


    public void publishRoutine() {
        while(true) {
            System.out.println("Wollen Sie ein FTS-Update veröffentlichen? Y/N");
            Scanner scanner = new Scanner(System.in);
            String intention = scanner.nextLine();
            if (intention.equals("y") || intention.equals("Y")) {
                System.out.println("Bitte geben Sie nun den Pfad zur XML-Datei an:");
                String filepath = scanner.nextLine();
                // work on XML file
            } else {
                System.out.println("Okay!");
            }
        }
    }

    public void publishFTSKnowledge(IBroker broker, String knowledge) {
        try {
            broker.publish(knowledge);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
