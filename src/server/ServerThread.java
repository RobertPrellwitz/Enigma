package server;
import enigma.PolyAlphabet;

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

public ServerThread (Socket s, PrintWriter write)
{
	sock = s;
	try {
	logWrite = write;
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
	String date=LocalDate.now().toString(); String internet= sock.getInetAddress().toString(); int port = sock.getPort();
	String outLine = ("New Connection: Date / Time: " + date +" Internet Addresss: " + internet + " Port#: "+ port);
	writeSock.println(outLine);
	System.out.println(outLine);
	boolean quitTime = false;
	 while( !quitTime )
	 {
	try
	{

	 String inLine = readSock.readLine(); 
	 String output = "";
	 // call PolyAlphabe
	 PolyAlphabet enigma = new PolyAlphabet(inLine);
	 output = (enigma.cipher(inLine));
	 writeSock.println(output);
	 //writeSock.println();
	 System.out.println();
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
