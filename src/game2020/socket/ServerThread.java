package game2020.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import game2020.Main;
import game2020.Player;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;



public class ServerThread extends Thread {
	private Socket welcomSocket;
	private Map<String, Player> players;			//TODO Overvej evt. en anden collection-framework, evt. HashMap med IP-adresser som key og player som value
	private int randomX;
	private int randomY;
	private String randomDirection;
	private int randomDirectionNumber;
	private static DataOutputStream outToClients; 

	
	public ServerThread(Socket welcomSocket) throws IOException {
		this.welcomSocket = welcomSocket;
		players = new HashMap<>();
		outToClients = new DataOutputStream(welcomSocket.getOutputStream());
	}

	@Override
	public void run() {
		String clientSentence;

		while (true) {
			try {
				BufferedReader inFromClient = new BufferedReader(new InputStreamReader(welcomSocket.getInputStream()));
				clientSentence = inFromClient.readLine();
				System.out.println("FROM EVENTHANDLEREN: " + clientSentence);

				//TODO "nySpiller": ny forbindelse til socket. Genererer position, retning og opretter ny spiller i serveren og sender info til gui (Main)
				if(clientSentence.startsWith("nySpiller")) {
					String[] splitted = clientSentence.split(" ");
					//random generate
					randomgeneratePositionAndDirection();
					String navn = splitted[2];
					Player p = new Player(navn, randomX, randomY, randomDirection);
					
					System.out.println(Main.getBoard());
					
					
//					Platform.runLater(() -> {
//							Main.addPlayers(p);
//							Label[][] fields = Main.fields;
//							Image[] heroDirections = Main.get_Herodirections();
//							System.out.println("fields: " + fields + ", herodirections: " + heroDirections);
//							fields[randomX][randomY].setGraphic(new ImageView(heroDirections[randomDirectionNumber]));
//					});
					
					
					try {
						outToClients.writeBytes("nySpiller_oprettet" + " " + navn + " " + randomX + " " + randomY + " " + randomDirection + '\n');
					} catch (IOException e) {
						e.printStackTrace();
					}
					String ip_addresse = splitted[1];
					players.put(ip_addresse, p);
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
