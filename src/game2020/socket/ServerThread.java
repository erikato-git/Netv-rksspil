package game2020.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import game2020.Player;

public class ServerThread extends Thread {
	Socket welcomSocket;
	ArrayList<Player> players;			//TODO Overvej evt. en anden collection-framework

	public ServerThread(Socket welcomSocket) {
		this.welcomSocket = welcomSocket;
		players = new ArrayList<>();
	}

	@Override
	public void run() {
		String clientSentence;

		while (true) {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(welcomSocket.getInputStream()));
				clientSentence = inFromClient.readLine();
				System.out.println("FROM EVENTHANDLEREN: " + clientSentence);
				
				switch(clientSentence) {
				//TODO "nySpiller": ny forbindelse til socket. Oprette ny spiller på gui'en, randomgenerer position og retning, broadcaster til socket
					case "nySpiller":
						
						break;
				//TODO "move": up, ned, venstre, højre. Opdaterer den pågældene spillers position, opdaterer point, broadcaster til socket
					case "move":
						System.out.println("SWITCH CASE: "+clientSentence);
						break;
				//TODO "skyd": Skydestråle fra pågældende spiller indtil den rammer mur eller modspiller
					case "skyd":
						
						break;
				//TODO "av": Den der går ind i en person får 10 point fra den bliver gået ind i, point opdateres
					case "av":
						
						break;
				//TODO "plus" / "minus": Point for den pågældende spiller opdateres og broadcastes til socket
					case "plus":
					case "minus":
						
						break;				
				
				default:
					System.out.println("INVALID INPUT");
				}
				
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}
}
