
package server;
import enigma.*;
import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Server enigma = new Server();
		enigma.run();
	}
	public void run () 
	{
		int portNum = 5525;
		
		try 
		{
		ServerSocket servSock = new ServerSocket(portNum);
		while (true) 
		{
			Socket sock = servSock.accept();
			ServerThread servThread = new ServerThread(sock);
			servThread.start();
		}
		}
		
		catch (IOException exp)
		{
			
		}
	}

}
