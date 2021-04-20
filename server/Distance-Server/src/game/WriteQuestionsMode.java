package game;

import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import main.GameController;
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
			String locationA = data.getString("locationA");
			String locationB = data.getString("locationB");
			upcoming.add(questionGenerator.create(locationA, locationB, unit));
			System.out.println(upcoming.peek().getPlaceA());
			if (upcoming.size()==players.size()) {
				super.sendQuestion();
			}
		}catch (JSONException e) {
			System.out.println("Feilet å sende til alle ");
		}
	}

	public String getGameInfo() {
		JSONObject info = super.getInfo();
		try {
			info.put("mode", GameController.WRITE_QUESTION_MODE);
			
		}catch (JSONException e) {
			
		}
		return info.toString();
	}
}
