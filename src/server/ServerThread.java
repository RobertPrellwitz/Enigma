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
		logWrite.println("IO Execption: " + except);
	}
}

public void run() 
{
	String date=LocalDateTime.now().toString(); String internet= sock.getInetAddress().toString(); int port = sock.getPort();String output = "";
	String outLine = ("New Connection: Date / Time: " + date +" Internet Addresss: " + internet + " Port#: "+ port);
	writeSock.println(outLine);
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
			String message = "Hello, Welcome to Enigma!  Please enter your text";
			//writeSock.flush();
			writeSock.println(message);
			//writeSock.flush();
			System.out.println(message);
		}
		else if( check.equals("quit"))
		{
			String close = "Connection Terminatated at: "+ LocalDateTime.now().toString();
			writeSock.println(close);
			writeSock.flush();
			logWrite.println(close);
			System.out.println(close);
			quitTime = true;
			sock.close();
			writeSock.close();
			readSock.close();
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
