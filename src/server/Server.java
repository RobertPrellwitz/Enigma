
package server;
import enigma.*;
import java.net.*;
import java.io.*;

public class Server {

	public static void main(String[] args) {

		Server enigma = new Server();
		enigma.run();
	}
	public void run () {
		int portNum = 5775;

		PrintWriter logWrite = null;
		try {
			ServerSocket servSock = new ServerSocket(portNum);
			logWrite = new PrintWriter(new FileOutputStream("prog2.log"), true);

			while (true) {
				Socket sock = servSock.accept();
				ServerThread servThread = new ServerThread(sock, logWrite);
				//servThread.run();
				servThread.start();
				
			}
		} catch (IOException exp) {

			logWrite.println("ErrorLog: " + exp);
		}
	}
}
