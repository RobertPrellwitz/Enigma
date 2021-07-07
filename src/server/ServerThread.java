package server;
import java.net.*;
import java.io.*;
import java.time.*;
import java.util.*;


class ServerThread extends Thread
{
Socket sock;
PrintWriter writeSock; // socketio
PrintWriter logWrite;  // log writer
BufferedReader readSock;

public ServerThread (Socket clientSock, PrintWriter writeSock) 
{
	sock = clientSock;
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
	String date=""; String internet=""; String port="";
	
//	date = Date().toString();  internet = sock.getInetAddress(); port = sock.getPort();
//	
	boolean quitTime = false;
	 while( !quitTime )
	 {
	try 
	{
	 String outLine = ("New Connection: Date / Time: " + date +" Internet Addresss: " + internet + " Port#: "+ port);
	 writeSock.println(outLine);
	 String inLine = readSock.readLine(); 
	 
	 // call PolyAlphabet
	 
	 if( inLine.equals("quit")) 
	 {
		 writeSock.println("connection closed");
		 quitTime = true;
		 sock.close();
	 }
	}
	 
	 
	 catch(IOException except) 
	 {
		 writeSock.println("Exception: "+ except);
	 }
	 }	
}
}
