The rmiregistry has to be executed newly for each start of the system.

Also Broker should be started new each time. Otherwise it will try to send a message to a reference which is not 
existing anymore. The concept does not catch not reachable errors