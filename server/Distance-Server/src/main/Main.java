package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.Game;
import game.NormalMode;
import game.Player;
import game.Unit;

public class Main {
	final public static int MAX_GAMES = 300;
	private static Main main = new Main( );
    private Main() { }
    
    public static Main getInstance( ) {
        return main;
    }
    // TODO: Har bare en server, hvordan blir det hvis vi utvider med flere 
	Server server;
	// TODO Har begrensning på antall spillere, trenger det være begrensning på antall spill??
	Game[] games = new Game[MAX_GAMES];
	HashMap<String, Game> gameCodes = new HashMap<String, Game>();
	List<Integer> activeGameCode = new ArrayList<Integer>();
	
	public  void startServer() {
		try {
			server = Server.getInstance();
			server.start(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Game createGame(Player player) {
		for ( int i = 0 ; i < MAX_GAMES ; i++ ) {
			if ( this.games[ i ] == null ) {
				String code = generateGameCode();
				Game newGame = new NormalMode(code, Unit.BANANA, i, player);
				gameCodes.put(code, newGame);
				this.games[ i ] = newGame;
				return newGame;
				
			}
		}
		return null;
		
	}
	public Game joinGame(String code, Player player) {
		return gameCodes.get(code);
	}
	
	public static void main(String[] args)
    {
        Main main = new Main();
        main.startServer();
    }
	private String generateGameCode() {
		String c = "";
		while (c=="" || gameCodes.get(c)!=null) {
			c = "";
			for (int i=0;i<5;i++) {
				c+=Math.round(Math.random() * 9);
			}
		}
		return c;
	}

	public void endGame(int id, String code) {
		games[id]=null;
		gameCodes.put(code, null);
	}
}
