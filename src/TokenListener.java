import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class TokenListener {

	public TokenListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String baseName = args[0];
		int myId = Integer.parseInt(args[1]);
		int numProc = Integer.parseInt(args[2]);
		int PortNumber = Integer.parseInt(args[3]);
		String request = "no Request";
		
		//Create structure and locks
	    Linker comm = null;
	    Lock lock = null;
		
	    try{
			comm = new Linker(baseName, myId, numProc);
			
			//Takes linker for communication and
			//Process number of the coordinator (here cooordinato is Pid 0)
			lock =  new CircToken(comm, 0);
			
			for(int i = 0; i < numProc; i++)
			{
				if(i != myId)
					new ListenerThread(i,(MsgHandler)lock).start();
			}
		}catch(InterruptedException e)
		{
			if(comm != null)
			{
				comm.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Now process the request
		
		DataInputStream input;
		
		ServerSocket MyService = null;
		Socket serviceSocket = null;
		try {
			MyService = new ServerSocket(PortNumber);
			  serviceSocket = MyService.accept();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      
		  while(true)
		  {
			  
		    try {

		       input = new DataInputStream(serviceSocket.getInputStream());
		       PrintWriter out = new PrintWriter(serviceSocket.getOutputStream(), true);
		       request = input.readLine();
		       
					if(request.equals("requestCS"))
					{
						lock.requestCS();
						System.out.println(myId + " is in CS *****");
						out.println("cs");
					}else{
						if(request.equals("releaseCS"))
						{
							lock.releaseCS();
							System.out.println(myId + " left CS *****");
							out.println("ok");
						}else{
							System.out.println("Invalid request - Try 'requestCS' or 'releaseCS'");
							out.println("error");
						}
					}
					
					Util.mySleep(5000);
					
				      
		        }
		        catch (IOException e) {
		           System.out.println(e);
		           break;
		            
		        }
		  }
		  if(MyService!=null)
		  {
		  MyService.close();  
		  }
		  
		  if(serviceSocket!=null)
		  {
		  serviceSocket.close();
		  }
	}

}
