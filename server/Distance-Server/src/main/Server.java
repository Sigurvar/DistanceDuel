package main;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.lang.Thread;

import game.Game;
import game.Player;
import game.Question;

public class Server extends Thread {// https://stackoverflow.com/questions/20753971/java-handling-multiple-client-sockets

	final private ServerSocket serverSocket;
	final public static int MAX_CLIENTS = 3000;
	final private Player[] players = new Player[ MAX_CLIENTS ];

	public Server( int port ) throws IOException {
		this.serverSocket = new ServerSocket( port );
		start();
		System.out.println("Starting socket.....");
	}

	@Override
	public void run() {
		while ( !this.interrupted() ) {
			//wait for clients
			Socket connection;
			try {
				connection = this.serverSocket.accept();
				assignConnectionToPlayer( connection );

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public void assignConnectionToPlayer( Socket connection ) {
		for ( int i = 0 ; i < MAX_CLIENTS ; i++ ) {

			//find an unassigned player
			if ( this.players[ i ] == null ) {
				System.out.println("Player " +i+" connected");
				this.players[ i ] = new Player( connection , i );
				break;
			}
		}
	}
	public void sendCode(Player player, String code) {
		
	}
	
	public void sendQuestion(Player player, Question question) {
		
	}
	
	public void partialResult(Player player, HashMap<Player,Integer> result, float solution) {
		
	}
	
	public void finishGame(Player player) {
		//player.getIp()
	}
	
	//public void finalResult(Player player, ArrayList<HashMap<Player,Integer>> results) {
	//}
	
	
	public static void main(String[] args)
    {
        try {
			Server server = new Server(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

