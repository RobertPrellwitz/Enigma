
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
		int portNum = 5764;

		PrintWriter writeSock = null;
		try {
			ServerSocket servSock = new ServerSocket(portNum);
			writeSock = new PrintWriter(new FileOutputStream("prog2.log"), true);

			while (true) {
				Socket sock = servSock.accept();
				ServerThread servThread = new ServerThread(sock, writeSock);
				servThread.run();
				//new ServerThread(servSock.accept().run());
			}
		} catch (IOException exp) {

			writeSock.println("ErrorLog: " + exp);
		}
	}
}
