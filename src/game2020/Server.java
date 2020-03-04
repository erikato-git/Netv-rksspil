package game2020;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import game2020.socket.*;

public class Server extends Thread {

	public static void main(String[] args) throws IOException {

		ServerSocket welcomSocket = new ServerSocket(9002);
		Socket connectionSocket = welcomSocket.accept(); // <- accept = 3-way handshake

		ServerThread threadWriteServer = new ServerThread(connectionSocket);	//Eventhandleren!!!
		ThreadTCPWriteServer threadReadServer = new ThreadTCPWriteServer(connectionSocket);

		threadWriteServer.start();
		threadReadServer.start();

	}

}
