package game2020.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game2020.Player;
import javafx.scene.control.Label;
import game2020.Main;

public class ServerThread extends Thread {
	Socket welcomSocket;
	Map<String, Player> players;			//TODO Overvej evt. en anden collection-framework, evt. HashMap med IP-adresser som key og player som value
	Main main;
	int randomX;
	int randomY;
	String randomDirection;
	
	public ServerThread(Socket welcomSocket) {
		this.welcomSocket = welcomSocket;
		players = new HashMap<>();
	}

	@Override
	public void run() {
		String clientSentence;

		while (true) {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(welcomSocket.getInputStream()));
				clientSentence = inFromClient.readLine();
				System.out.println("FROM EVENTHANDLEREN: " + clientSentence);

				//TODO "nySpiller": ny forbindelse til socket. Oprette ny spiller på gui'en, randomgenerer position og retning, broadcaster til socket
				if(clientSentence.startsWith("nySpiller")) {
					String[] splitted = clientSentence.split(" ");
					//random generate
					randomgeneratePosition();
					System.out.println(splitted[1]);
					//opret spiller med random-genereret position
//					players.put(splitted[1], p);
					
				//TODO "move": up, ned, venstre, højre. Opdaterer den pågældene spillers position, opdaterer point, broadcaster til socket
				}else if(clientSentence.startsWith("move")){

					
				//TODO "skyd": Skydestråle fra pågældende spiller indtil den rammer mur eller modspiller
				}else if(clientSentence.startsWith("skyd")){

					
				//TODO "av": Den der går ind i en person får 10 point fra den bliver gået ind i, point opdateres
				}else if(clientSentence.startsWith("av")){

					
				//TODO "plus" / "minus": Point for den pågældende spiller opdateres og broadcastes til socket
				}else if(clientSentence.startsWith("plus") || clientSentence.startsWith("plus")){
					
					
				}else{
					System.out.println("INVALID INPUT");
				}
				

			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	private void randomgeneratePosition() {
		boolean foundPosition = false;
		while(!foundPosition) {
			randomX = (int) Math.floor(Math.random() * 20);
			randomY = (int) Math.floor(Math.random() * 20);
			String[] board = main.getBoard();
			if(main.getPlayerAt(randomX, randomY) == null && board[randomY].charAt(randomX) != 'w') {
				foundPosition = true;
			}
		}
		//direction
		randomDirection = 
		
		
	}
}
