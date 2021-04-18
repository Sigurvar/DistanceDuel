package main;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import game.Game;
import game.NormalMode;
import game.Player;
import game.Unit;
import game.WriteQuestionsMode;

public class GameController {
	
	/** Game Modes **/
    protected static final int NORMAL_MODE = 1;
    protected static final int WRITE_QUESTION_MODE = 2;

	
	final public static int MAX_GAMES = 300;
	
	private static GameController gameController = new GameController( );
    private GameController() { }
    
    public static GameController getInstance( ) {
        return gameController;
    }

    Game[] games = new Game[MAX_GAMES];
	HashMap<String, Game> gameCodes = new HashMap<String, Game>();
    
    public Game createGame(Player player, String settings) {
    	//TODO make this better
		for ( int i = 0 ; i < MAX_GAMES ; i++ ) {
			if ( this.games[ i ] == null ) {
				String code = generateGameCode();
				try {
					int gameMode = new JSONObject(settings).getInt("gameMode");
					Game newGame = null;
					if (gameMode==NORMAL_MODE) {
						newGame = new NormalMode(code, Unit.BANANA, i, player);
					}else if (gameMode == WRITE_QUESTION_MODE){
						newGame = new WriteQuestionsMode(code, Unit.BANANA,i,player);
					}
					gameCodes.put(code, newGame);
					this.games[ i ] = newGame;
					return newGame;
				} catch (JSONException e) {
					e.printStackTrace();
					return null;
				}
				
				
			}
		}
		return null;
		
	}
	public Game joinGame(String code, Player player) {
		return gameCodes.get(code);
	}
	public void endGame(int id, String code) {
		games[id]=null;
		gameCodes.put(code, null);
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

	
}
