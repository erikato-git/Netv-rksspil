package game2020.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadTCPReadClient extends Thread {
	Socket clientSocket;

	public ThreadTCPReadClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		String modifiedSentence;

		while (true) {
			try {
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				modifiedSentence = inFromServer.readLine();
				System.out.println("FROM SERVER: " + modifiedSentence);
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}
}
