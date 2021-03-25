package main;

import java.io.IOException;

import game.Game;
import game.NormalMode;
import game.Player;
import game.Unit;

public class Main {
	
	private static Main main = new Main( );

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Main() { }

    /* Static 'instance' method */
    public static Main getInstance( ) {
        return main;
    }
	Server server;
	Game[] games = new Game[100];
	public  void startServer() {
		try {
			server = new Server(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Game createGame(String code, Player player) {
		Game newGame = new NormalMode(code, Unit.BANANA);
		for ( int i = 0 ; i < 100 ; i++ ) {

			//find an unassigned player
			if ( this.games[ i ] == null ) {
				System.out.println("Player " +i+" connected");
				this.games[ i ] = newGame;
				break;
			}
		}
		return newGame;
	}
	public Game joinGame(String code, Player player) {
		for ( int i = 0 ; i < 100 ; i++ ) {

			if ( this.games[ i ] != null ) {
				System.out.println("Game not null");
				System.out.println(this.games[i]);
				if (this.games[i].join(code, player)) {
					return this.games[i];
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args)
    {
        Main main = new Main();
        main.startServer();
    }
}
