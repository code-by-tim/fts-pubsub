package FTSLocalSystem;

import java.rmi.RemoteException;

public class Publisher {


    public void publishRoutine() {

    }

    public void publishFTSKnowledge(IBroker broker, String knowledge) {
        try {
            broker.publish(knowledge);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
