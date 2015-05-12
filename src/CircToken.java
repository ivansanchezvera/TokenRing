import java.util.Timer;

//This is a centralized version of the token algorithm,
//in which the coordinator is responsible for keeping the token.
//All the request for the token go to the coordinator
//The algorithm is initiated by the coordinator who sends the token 
//to the next process in the ring.
//By ensuring that a process enters the CS only when in possession of 
//the token this algorithm satisfies the safety property trivially.
public class CircToken extends Process implements Lock {

	boolean haveToken; //this has the local state of the process.
	boolean wantCS = false;
	
	//Takes linker for communication and
	//Process number of the coordinator
	public CircToken(Linker initComm, int coordinator) {
		super(initComm);
		haveToken = (myId == coordinator);
		// TODO Auto-generated constructor stub
	}

	public synchronized void initiate()
	{
		if (haveToken) sendToken();
	}

	@Override
	public synchronized void requestCS() {
		// TODO Auto-generated method stub
		//If we want the Critical section but have no token, then wait
		wantCS = true;
		while(!haveToken) myWait();
	}

	@Override
	public synchronized void releaseCS() {
		// TODO Auto-generated method stub
		wantCS = false;
		sendToken();
		
	}

	private void sendToken() {
		// TODO Auto-generated method stub
		if(haveToken && !wantCS)
		{
			//send to next process
			int next = (myId + 1) % N;
			//System.out.println("Process " + myId + "has sent the token");
			Util.println("Process " + myId + "has sent the token");
			sendMsg(next, "token");
			haveToken = false;
		}
	}
	
	public synchronized void handleMsg(Msg m, int src, String tag)
	{
		if (tag.equals("token"))
		{
			haveToken = true;
			if(wantCS)
			{
				notify();
			}else{
				Util.mySleep(1000);
				sendToken();
			}
		}
	}
}
