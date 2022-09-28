package FTSLocalSystem;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISubscriber extends Remote {
    void notify(String knowledgeUpdate) throws RemoteException;
}
