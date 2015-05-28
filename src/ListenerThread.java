import java.io.IOException;

public class ListenerThread extends Thread {

	int channel;
	MsgHandler process;
	
	public ListenerThread(int channel, MsgHandler process) {
		// TODO Auto-generated constructor stub
		this.channel = channel;
		this.process = process;
	}
	
	public void run()
	{
		while(true){
			try{
				//This is from pages 130 to 132
				//This is a blocking receiveMessage Call
				Msg m = process.receiveMsg(channel);
				//Now give the message to the Message Handler
				process.handleMsg(m, m.getSrcId(), m.getTag());
			}catch(IOException e)
			{
				System.err.println(e);
			}
		}
	}

}
