package FTSLocalSystem;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Class representing the entry point into the client side application.
 */
public class FTSApp {
    /**
     * This method first initializes central variables. Afterwards it obtains a reference to the Broker on the Server through the RMI Registry.
     * It then subscribes to Broker Updates and enters the publishing Routine.
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            // Initialize central variables
            Scanner scanner = new Scanner(System.in);
            KnowledgeBase knowledgeBase = new KnowledgeBase(scanner);
            Subscriber subscriber = new Subscriber(knowledgeBase);
            MainMenu mainMenu = new MainMenu(knowledgeBase, scanner);

            // Find Broker through the RMI-Registry
            Registry registry = LocateRegistry.getRegistry();
            IBroker brokerStub = (IBroker) registry.lookup("broker");
            if(brokerStub != null) System.out.println("FTSApp: Server gefunden.");

            // Subscribe to FTS-Knowledge Updates
            brokerStub.subscribe(subscriber);

            // Run input routine
            mainMenu.inputRoutine(brokerStub);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
