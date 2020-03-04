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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import game2020.Main;

public class ServerThread extends Thread {
	private Socket welcomSocket;
	private Map<String, Player> players;			//TODO Overvej evt. en anden collection-framework, evt. HashMap med IP-adresser som key og player som value
	private int randomX;
	private int randomY;
	private String randomDirection;
	private int randomDirectionNumber;
	
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

				//TODO "nySpiller": ny forbindelse til socket. Oprette ny spiller på gui'en, randomgenerer position og retning
				if(clientSentence.startsWith("nySpiller")) {
					String[] splitted = clientSentence.split(" ");
					//random generate
					randomgeneratePositionAndDirection();
					String navn = splitted[2];
					Player p = new Player(navn, randomX, randomY, randomDirection);

					//TODO Man kan diskutere om gui-manipulationen skal foregå her eller sende kald til Main-klassen 
					Main.addPlayers(p);
					Label[][] fields = Main.fields;
					Image[] heroDirections = Main.get_Herodirections();
					System.out.println("fields: " + fields + ", herodirections: " + heroDirections);
					fields[randomX][randomY].setGraphic(new ImageView(heroDirections[randomDirectionNumber]));
					

					players.put(splitted[1], p);
				//TODO "move": up, ned, venstre, højre. Opdaterer den pågældene spillers position, opdaterer point
				}else if(clientSentence.startsWith("move")){

					
				//TODO "skyd": Skydestråle fra pågældende spiller indtil den rammer mur eller modspiller
				}else if(clientSentence.startsWith("skyd")){

					
				//TODO "av": Den der går ind i en person får 10 point fra den bliver gået ind i, point opdateres
				}else if(clientSentence.startsWith("av")){

					
				//TODO "plus" / "minus": Point for den pågældende spiller opdateres
				}else if(clientSentence.startsWith("plus") || clientSentence.startsWith("plus")){
					
					
				}else{
					System.out.println("INVALID INPUT");
				}
				

			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

	
	private void randomgeneratePositionAndDirection() {
		boolean foundPosition = false;
		while(!foundPosition) {
			randomX = (int) Math.floor(Math.random() * 20);
			randomY = (int) Math.floor(Math.random() * 20);
			String[] board = Main.getBoard();
			if(Main.getPlayerAt(randomX, randomY) == null && board[randomY].charAt(randomX) != 'w') {
				foundPosition = true;
			}
		}
		String directions[] = {"right","left","up","down"};
		randomDirectionNumber = (int) Math.floor(Math.random() * 4);
		randomDirection = directions[randomDirectionNumber];
	}
}
