import java.util.*;
public class Util {
	
	public static int max(int a, int b)
	{
		if(a>b)
		{
			return a;
		}else
		{
			return b;
		}
	}
	
	public static void mySleep(int time)
	{
		try{
			Thread.sleep(time);
		}catch(InterruptedException e){
			//Do nothing here
		}
	}
	
	public static void myWait(Object obj)
	{
		println("waiting");
		try{
			obj.wait();
		}catch(InterruptedException e){
			
		}
	}
	
	//Some other methods like:
	//LessThan
	//MaxArray
	//WriteArray
	//ReadArray
	//SearchArray
	
	public static void println(String s)
	{
		if(Symbols.debugFlag)
		{
			System.out.println(s);
			System.out.flush();
		}
	}

}
