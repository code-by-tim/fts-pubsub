package FTSLocalSystem;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FTSApp {

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
