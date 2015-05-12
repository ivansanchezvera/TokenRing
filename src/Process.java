import java.io.IOException;


public class Process implements MsgHandler {
	
	int N, myId;
	Linker comm;
	
	public Process(Linker initComm) {
		// TODO Auto-generated constructor stub
		comm = initComm;
		myId = comm.getMyId();
		N = comm.getNumProc();
	}
	
	//Every class that extends Process is expected to override this method.
	@Override
	public synchronized void handleMsg(Msg m, int srcId, String tag) {
		//Do nothing, should be overriden.
	}
	
	public void sendMsg(int destId, String tag, String msg)
	{
		//This was Util.println in the original one
		//System.out.println("Sending msg to " + destId + ":" + tag + " " + msg);
		Util.println("Sending msg to " + destId + ":" + tag + " " + msg);
		comm.sendMsg(destId, tag, msg);
	}
	
	public void sendMsg(int destId, String tag, int msg)
	{
		sendMsg(destId, tag, String.valueOf(msg) + " ");
	}
	
	public void sendMsg(int destId, String tag, int msg1, int msg2)
	{
		sendMsg(destId, tag, String.valueOf(msg1) + " " + String.valueOf(msg2));
	}
	
	public void sendMsg(int destId, String tag)
	{
		sendMsg(destId, tag, " 0 ");
	}
	
	public void broadcastMsg(String tag, int msg)
	{
		for(int i = 0; i < N; i++)
		{
			if(i!=myId)
			{
				sendMsg(i, tag, msg);
			}
		}
	}
	
	public void sendToNeighbors(String tag, int msg)
	{
		for(int i = 0; i<N; i++)
		{
			if(i!=myId)
			{
				sendMsg(i, tag, msg);
			}
		}
	}
	
	public boolean isNeighbor(int i)
	{
		//This could be a single line
		return comm.neighbors.contains(i);
	}
	
	@Override
	public Msg receiveMsg(int fromId)
	{
		try{
			return comm.receiveMsg(fromId);
		}catch(IOException e)
		{
			System.out.println(e);
			comm.close();
			return null;
		}
	}

	public synchronized void myWait()
	{
		try{
			wait();
		}catch(InterruptedException e)
		{
			System.err.println(e);
		}
	}

}
