package game2020.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadTCPWriteServer extends Thread {
	Socket welcomSocket;

	public ThreadTCPWriteServer(Socket welcomSocket) {
		this.welcomSocket = welcomSocket;
	}

	@Override
	public void run() {
		String userSentence;

		while (true) {
			try {
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				DataOutputStream outToClient = new DataOutputStream(this.welcomSocket.getOutputStream());

				userSentence = inFromUser.readLine();
				outToClient.writeBytes(userSentence + '\n');
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}
}
