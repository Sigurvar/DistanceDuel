package game;

import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import main.GameController;
import main.QuestionGenerator;

public class ChallengeMode extends Game {

	QuestionGenerator questionGenerator = new QuestionGenerator();
	
	public ChallengeMode(String code, Player creator) {
		super(code, Unit.randomUnit(), creator);
		upcoming = new Stack<>();
	}

	@Override
	public void startGame() {
		super.startGame();
		for (Player p : players) {
			p.outputThread.sendCreateQuestion();
		}
		
	}
	
	public void userGeneratedQuestion(Player player, String questionData) {
		try { 
			JSONObject data = new JSONObject(questionData);
			String locationA = data.getString("locationA");
			String locationB = data.getString("locationB");
			upcoming.add(questionGenerator.create(locationA, locationB, unit));
			System.out.println(upcoming.peek().getLocationA());
			if (upcoming.size()==players.size()) {
				super.sendQuestion();
			}
		}catch (JSONException e) {
			e.printStackTrace();
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
