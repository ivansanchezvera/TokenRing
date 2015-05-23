Readme

************************************SETUP***************************************
To execute.
Execute name server first, NameServer.java with no parameters.

After the name server is running, all nodes should point to this name server, so they
can register their names and the topology can be stablished.

To run each token ring node:
execute TokenListener.java
With parameters like:
test 0 3 2250

1st parameter: Basename (all nodes should have same basename for a given ring)
2nd parameter: NodeID (the id should be an integer, in sequence, starting from 0 to numNodes - 1)
3rd parameter: Total number of nodes (in this case 3). Should be the same for each node in a given topology.
4th parameter: Listening Port for requests.

all nodes should be running in order to get the topology right, that is number of 
nodes runnig = Total number of nodes (3rd parameter defined in the each single node)

**************************REQUESTS***************************************
Once nodes are running, they are listening for requests:
To request Critical section (lock) = requestCS
Response:
cs = the critical section you requested is given to you. You have the
lock!

To release Critical section (unlock) = releaseCS
Response:
ok = the critical section have been released properly. You have released
the lock!

