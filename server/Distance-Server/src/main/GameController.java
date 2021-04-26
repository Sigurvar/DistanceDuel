package main;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import game.Game;
import game.NormalMode;
import game.Player;
import game.Unit;
import game.ChallengeMode;

public class GameController {
	
	/** Game Modes **/
    public static final int NORMAL_MODE = 1;
    public static final int WRITE_QUESTION_MODE = 2;

	
	private static GameController gameController = new GameController( );
    private GameController() { }
    
    public static GameController getInstance( ) {
        return gameController;
    }

	HashMap<String, Game> gameCodes = new HashMap<String, Game>();
    
    public Game createGame(Player player, String settings) {
		String code = generateGameCode();
		try {
			int gameMode = new JSONObject(settings).getInt("gameMode");
			Game newGame = null;
			if (gameMode==NORMAL_MODE) {
				newGame = new NormalMode(code, player);
			}else if (gameMode == WRITE_QUESTION_MODE){
				newGame = new ChallengeMode(code,player);
			}
			gameCodes.put(code, newGame);
			return newGame;
		} catch (JSONException e) {
			return null;
		}
		
	}
	public Game joinGame(String code, Player player) {
		return gameCodes.get(code);
	}
	public void endGame(String code) {
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
