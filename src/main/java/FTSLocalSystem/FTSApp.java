package FTSLocalSystem;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Class representing the entry point into the client side application.
 */
public class FTSApp {

    /**
     * This method obtains a reference to the Broker on the Server through the RMI Registry.
     * It then subscribes to Broker Updates and enters the publishing Routine.
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            Publisher publisher = new Publisher();
            Subscriber subscriber = new Subscriber();

            // Find Broker through the RMI-Registry
            Registry registry = LocateRegistry.getRegistry();
            IBroker brokerStub = (IBroker) registry.lookup("broker");
            if(brokerStub != null) System.out.println("Server gefunden.");

            // Subscribe to FTS-Knowledge Updates
            brokerStub.subscribe(subscriber);

            // Run publish routine
            publisher.publishRoutine(brokerStub);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fuck Error");
        }
    }
}
