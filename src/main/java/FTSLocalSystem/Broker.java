package FTSLocalSystem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Broker extends UnicastRemoteObject implements IBroker {

    private String ftsKnowledge = "";

    static Semaphore semaphorePub = new Semaphore(1);
    static Semaphore semaphoreSub = new Semaphore(1);
    HashSet<ISubscriber> subscriberSet = new HashSet<>();

    public Broker() throws RemoteException {
    }

    @Override
    public void publish(String ftsObject) {
        try {
            semaphorePub.acquire();
            try{
                this.updateKnowledgeBase(ftsObject);
                this.informSubscribers(ftsObject);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphorePub.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        try {
            semaphoreSub.acquire();
            try{
                subscriberSet.add(subscriber);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphoreSub.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateKnowledgeBase(String ftsUpdate) {
        ftsKnowledge += ftsUpdate;
        System.out.println("Knowledge Base updated!");
    }

    private void informSubscribers(String ftsUpdate) {
        subscriberSet.forEach(subscriber -> {
            try {
                subscriber.notify(ftsUpdate);
            } catch (RemoteException e) {
                e.printStackTrace();
                // TODO: Change to not reachable instead of stack trace.
            }
        });
        System.out.println("Subscribers were informed about the updates!");
    }
}
