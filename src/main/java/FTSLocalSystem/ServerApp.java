package FTSLocalSystem;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerApp {

    public static void main(String[] args) {

        // TODO: FTSLocalSystem.Broker-Objekt erzeugen
        try {
            Broker broker = new Broker();

            // TODO: FTSLocalSystem.Broker in RMI-Registry eintragen
            //Registry registry = LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry(); // execute registry command in ..\java\main\
            registry.bind("broker", broker); // Bind object to registry to inform others
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
