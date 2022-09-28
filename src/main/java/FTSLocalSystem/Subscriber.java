package FTSLocalSystem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

public class Subscriber extends UnicastRemoteObject implements ISubscriber {

    static Semaphore semaphore = new Semaphore(1);
    public Subscriber() throws RemoteException {}

    @Override
    public void notify(String knowledgeUpdate) {
        try {
            semaphore.acquire();
            try{
                System.out.println(knowledgeUpdate);
                System.out.println("We were here");
            } catch (Exception e) {

            } finally {
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
