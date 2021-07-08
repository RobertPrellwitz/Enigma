
package server;
import enigma.*;
import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {

		Server enigma = new Server();
		enigma.run();
	}
	public void run () 
	{
		int portNum = 5764;
		
		try 
		{
		ServerSocket servSock = new ServerSocket(portNum);
		PrintWriter writeSock = new PrintWriter(new FileOutputStream("prog2.log"),true);
		int i = 150;
		int counter =0;
		while (counter < i)
		{
			Socket sock = servSock.accept();
			ServerThread servThread = new ServerThread(sock, writeSock);
			servThread.run();
			counter++;
		}
		}
		
		catch (IOException exp)
		{
			System.out.println("ErrorLog: "+ exp);
		}	
	}
}
