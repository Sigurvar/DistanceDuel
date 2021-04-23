package game;

import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import main.GameController;
import main.QuestionGenerator;

public class NormalMode extends Game {

	public NormalMode(String code, Unit unit, int id, Player creator) {
		super(code, unit, id, creator);
	}

	@Override
	public void startGame() {
		if(super.gameHasStarted) {return;}
		super.startGame();
		QuestionGenerator questionGenerator = new QuestionGenerator();
		try {
			upcoming = questionGenerator.generate(unit, 7);
		} catch (FileNotFoundException | JSONException e) {
			e.printStackTrace();
		}
		super.sendQuestion();
	}
	
	public String getGameInfo() {
		JSONObject info = super.getInfo();
		try {
			info.put("mode", GameController.NORMAL_MODE);
			
		}catch (JSONException e) {
			
		}
		return info.toString();
	}

}
