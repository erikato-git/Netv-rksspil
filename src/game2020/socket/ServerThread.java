package game2020.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket welcomSocket;

	public ServerThread(Socket welcomSocket) {
		this.welcomSocket = welcomSocket;
	}

	@Override
	public void run() {
		String clientSentence;

		while (true) {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(welcomSocket.getInputStream()));
				clientSentence = inFromClient.readLine();
				System.out.println("FROM EVENTHANDLEREN: " + clientSentence);
				
				
				
				
				
				
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}
}
