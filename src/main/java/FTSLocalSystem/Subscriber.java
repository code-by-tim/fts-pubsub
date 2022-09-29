package FTSLocalSystem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

/**
 * This class is the remote object given by the Clients to the server when subscribing.
 * It provides methods for the server to call on knowledge updates.
 */
public class Subscriber extends UnicastRemoteObject implements ISubscriber {

    // Semaphores to make sure the methods are only executed atomically by one thread at a time.
    // Even when called at the same time from different hosts.
    static Semaphore semaphore = new Semaphore(1);
    KnowledgeBase knowledgeBase;

    /**
     * Constructor assigning the knowledge Base to the respective variable.
     * @param knowledgeBase
     * @throws RemoteException
     */
    public Subscriber(KnowledgeBase knowledgeBase) throws RemoteException {
        this.knowledgeBase = knowledgeBase;
    }

    /**
     * This method is to be called by the server upon knowledge updates, which are relevant for this client.
     * The method notifies the user of the received update, prints it out and merges it with the knowledge base.
     * @param knowledgeUpdate manufacturing technology scouting knowledge as a String in the standard XML format
     */
    @Override
    public void notify(String knowledgeUpdate) {
        try {
            semaphore.acquire();
            try{
                System.out.println("Subscriber: Das folgende FTS-Knowledge-Update wurde empfangen:");
                System.out.println(knowledgeUpdate);
                knowledgeBase.mergeWithKnowledgeBase(knowledgeUpdate);
            } catch (Exception e) {

            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
