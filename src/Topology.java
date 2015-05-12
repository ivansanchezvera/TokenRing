import java.io.*;
import java.util.*;

public class Topology {
	
	//Read the topology of the underlying network
	//List of neighbours of Pi are assumed to me enumerated in the file "topologyi"
	public static void readNeighbors(int myId, int N, IntLinkedList neighbors)
	{
		//System.out.println("Reading topology");
		Util.println("Reading topology");
		
		try{
			BufferedReader dIn = new BufferedReader(
					new FileReader("topology" + myId));
			StringTokenizer st = new StringTokenizer(dIn.readLine());
			while(st.hasMoreTokens())
			{
				int neighbor = Integer.parseInt(st.nextToken());
				neighbors.add(neighbor);
			}
		}catch(FileNotFoundException e)
		{
			for(int j = 0; j < N; j++)
				if(j != myId)
					neighbors.add(j);
		}catch(IOException e)
		{
			System.err.println(e);
		}
		//Util
		System.out.println(neighbors.toString());
		
	}
}
