package server;
import java.net.*;
import java.io.*;

class ServerThread extends Thread
{
Socket sock;
PrintWriter writeSock;
BufferedReader readSock;

public ServerThread (Socket plug) 
{
	sock = plug;
	try {
	writeSock = new PrintWriter(sock.getOutputStream(),true);
	readSock = new BufferedReader( new InputStreamReader(
			 sock.getInputStream() ) );
	}
	catch(IOException except) 
	{
		
	}
	
}

public void run() 
{
	boolean quitTime = false;
	 while( !quitTime )
	 {
	try 
	{
	 String inLine = readSock.readLine(); 
	 String outLine = inLine + "HaHa!" ;
	 writeSock.println( outLine );
	 if( inLine.equals("quit"))
	 quitTime = true;
	 sock.close();
	 }
	 
	 
	 catch(IOException except) 
	 {
 
	 }
	 }	
}
}
