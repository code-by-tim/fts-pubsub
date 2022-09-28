package FTSLocalSystem;

import FTSLocalSystem.ISubscriber;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBroker extends Remote {
    void publish(String ftsObject) throws RemoteException;

    void subscribe(ISubscriber subscriber) throws RemoteException;
}
