package FTSLocalSystem;

import FTSLocalSystem.ISubscriber;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Used to inform remote processes about the methods of the implementing class (Broker).
 */
public interface IBroker extends Remote {

    /**
     * Method is intended to be called by remote processes /client system processes to publish their knowledge updates.
     * @param ftsObject String in XML-Format containing updates about manufacturing technology scouting
     * @throws RemoteException
     */
    void publish(String ftsObject) throws RemoteException;

    /**
     * Method is intended to be used as a way to subscribe to updates from other clients.
     * @param subscriber RemoteObject providing methods to call on updates
     * @throws RemoteException
     */
    void subscribe(ISubscriber subscriber) throws RemoteException;
}
