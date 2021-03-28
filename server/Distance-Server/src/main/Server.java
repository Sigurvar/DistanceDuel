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
		System.out.println("Starting socket.....");
		start();
	}

	@Override
	public void run() {
		System.out.println("Running...");
		while ( !this.interrupted() ) {
			System.out.println("Ready to receive new player");
			Socket connection;
			try {
				connection = this.serverSocket.accept();
				assignConnectionToPlayer( connection );
			} catch (IOException e) {
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
}

