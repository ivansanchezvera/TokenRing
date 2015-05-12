import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;


public class NameServer {

	NameTable table;

	public NameServer() {
		table = new NameTable();
	}
	
	void hadleclient(Socket theClient)
	{
		try{
			BufferedReader din = new BufferedReader(new InputStreamReader(theClient.getInputStream()));
			PrintWriter pout = new PrintWriter(theClient.getOutputStream());
			String getline = din.readLine();
			StringTokenizer st = new StringTokenizer(getline);
			String tag = st.nextToken();
			
			if(tag.equals("search"))
			{
				int index = table.search(st.nextToken());
				if(index==-1) //not found
				{
					pout.println(-1 + " " + "nullhost");
				}else{
					pout.println(table.getPort(index) + " "
					+table.getHostName(index));
				}
			} else if(tag.equals("insert"))
			{
				String name = st.nextToken();
				String hostName = st.nextToken();
				int port = Integer.parseInt(st.nextToken());
				int retValue = table.insert(name, hostName, port);
				pout.println(retValue);
			}
			pout.flush();
		}catch(IOException e)
		{
			System.err.println(e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NameServer ns = new NameServer();
		System.out.println("NameServer started:");
		try{
			ServerSocket listener = new ServerSocket(Symbols.ServerPort);
			while(true)
			{
				Socket aClient = listener.accept();
				ns.hadleclient(aClient);
				aClient.close();
			}
		}catch(IOException e){
			System.err.println("Server aborted:" + e);
		}
	}

}
