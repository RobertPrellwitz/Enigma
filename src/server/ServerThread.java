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
	String date=LocalDateTime.now().toString(); String internet= sock.getInetAddress().toString(); int port = sock.getPort();String output = "";
	String outLine = ("New Connection: Date / Time: " + date +" Internet Addresss: " + internet + " Port#: "+ port);
	writeSock.println(outLine);
	writeSock.flush();
	System.out.println(outLine);
	logWrite.println(outLine);

	boolean quitTime = false;
	 while( !quitTime )
	 {
	try
	{

	 String inLine = readSock.readLine();
	 String check = inLine.toLowerCase();
		if (check.equals("hello"))
		{
			writeSock.println("Hello and Welcome. Please enter your text!");
			writeSock.flush();
		}
		else if( check.equals("quit"))
		{
			writeSock.println("connection closed");
			writeSock.flush();
			logWrite.println("Connection Terminatated at: "+ LocalDateTime.now().toString());
			quitTime = true;
			sock.close();
		}
		else
		{
			PolyAlphabet enigma = new PolyAlphabet(inLine);
			output = (enigma.cipher(inLine));
			writeSock.println(output);
			System.out.println(output);
			writeSock.flush();
		}
	}
	 
	 
	 catch(IOException except) 
	 {
		 writeSock.println("Exception: "+ except);
		 logWrite.println("Exception: "+ except);
	 }
	 }	
}
}
