package game2020.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ThreadTCPWriteClient extends Thread {
	Socket clientSocket;

	public ThreadTCPWriteClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		String sentence;

		while (true) {
			try {
				BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
				DataOutputStream outToServer = new DataOutputStream(this.clientSocket.getOutputStream());

				sentence = inFromUser.readLine();
				outToServer.writeBytes(sentence + '\n');
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}
}
