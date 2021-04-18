package game;

import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import main.QuestionGenerator;

public class WriteQuestionsMode extends Game {

	QuestionGenerator questionGenerator = new QuestionGenerator();
	
	public WriteQuestionsMode(String code,Unit unit, int id, Player creator) {
		super(code, unit, id, creator);
		upcoming = new Stack<>();
	}

	@Override
	public void startGame() {
		for (Player p : players) {
			p.outputThread.sendCreateQuestion();
		}
	}
	
	public void userGeneratedQuestion(Player player, String questionData) {
		try {
			// TODO: create writequestionmode with player as owner 
			JSONObject data = new JSONObject(questionData);
			String placeA = data.getString("placeA");
			String placeB = data.getString("placeB");
			upcoming.add(questionGenerator.create(placeA, placeB, unit));
			if (upcoming.size()==players.size()) {
				super.sendQuestion();
			}
		}catch (JSONException e) {
			
		}
	}

}
