package FTSLocalSystem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class Subscriber extends UnicastRemoteObject implements ISubscriber {

    static Semaphore semaphore = new Semaphore(1);
    KnowledgeBase knowledgeBase;

    public Subscriber(KnowledgeBase knowledgeBase) throws RemoteException {
        this.knowledgeBase = knowledgeBase;
    }

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
