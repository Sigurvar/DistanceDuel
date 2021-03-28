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
    
	Server server;
	// TODO Har begrensning på antall spillere, trenger det være begrensning på antall spill??
	Game[] games = new Game[MAX_GAMES];
	HashMap<String, Game> gameCodes = new HashMap<String, Game>();
	List<Integer> activeGameCode = new ArrayList<Integer>();
	
	public  void startServer() {
		try {
			server = new Server(8888);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Game createGame(Player player) {
		for ( int i = 0 ; i < MAX_GAMES ; i++ ) {
			if ( this.games[ i ] == null ) {
				String code = generateGameCode();
				while (gameCodes.get(code)!=null) {
					code = generateGameCode();
				}
				Game newGame = new NormalMode(code, Unit.BANANA);
				gameCodes.put(code, newGame);
				System.out.println("Game created by "+player.getNickname()+ " with code "+code);
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
		for (int i=0;i<5;i++) {
			c+=Math.round(Math.random() * 5);
		}
		return c;
	}
}
