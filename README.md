The rmiregistry has to be executed newly for each start of the system.

Also Broker should be started new each time. Otherwise it will try to send a message to a reference which is not 
existing anymore. The concept does not catch not reachable errors

FTS = MTS in the english documentation

An easy way to copy a file path from the windows Explorer is to press Shift and right-click the file. Then choose "Copy as filepath"

Registry registry = LocateRegistry.getRegistry(); // execute registry command in ..\java\main\