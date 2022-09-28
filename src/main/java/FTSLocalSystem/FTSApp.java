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
            if(brokerStub != null) System.out.println("BrokerStub found.");

            // Subscribe to FTS-Knowledge Updates
            brokerStub.subscribe(subscriber);

            // TODO: Implement Publishing

            //brokerStub.publish("Some Knowledge about FTS");
            publisher.publishRoutine();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fuck Error");
        }
    }
}
