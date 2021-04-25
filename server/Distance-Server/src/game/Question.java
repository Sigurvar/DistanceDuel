package game;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class Question {
	
	private String locationA;
	private String locationB;
	private double correctAnswer;
	private Unit unit;
	private JSONObject currentAnswers= new JSONObject();
	private final double maxScore = 1500;
	
	public Question(String placeA, String placeB, double correctAnswer, Unit unit) {
		this.locationA = placeA;
		this.locationB = placeB;
		this.correctAnswer = correctAnswer;
		this.unit = unit;
	}

	public long calculateScore(double userAnswer) {
		double percentageIncorrect = Math.abs(1 - Math.min((userAnswer / correctAnswer), 2));
		long score = Math.round(maxScore - (maxScore * percentageIncorrect));
		System.out.println(score);
		return score;		
	}
	
	public JSONObject getQuestion() {
		JSONObject question = new JSONObject();
		try {
			
			question.put("locationA", locationA);
			question.put("locationB", locationB);
			question.put("unit", unit.name());
			
		}catch (JSONException e) {
			
		}
		return question;
	}

	public String getLocationA() {
		return locationA;
	}
	
	
	public String getLocationB() {
		return locationB;
	}
	
	
	public double getCorrectAnswer() {
		return correctAnswer;
	}
	
	
	public Unit getUnit() {
		return unit;
	}
	
	public void playerAnswered(Player player, double answer) {
		JSONObject jPlayer = new JSONObject();
		try {
			jPlayer.put("Answer", answer);
			jPlayer.put("Score", calculateScore((double)answer));
			this.currentAnswers.append(player.getNickname(), jPlayer);
		} catch (JSONException e) {
		}
	}
	
	public ArrayList<String> getPlayersWhoHaveAnswered() {
		ArrayList<String> playerNames = new ArrayList<>();
		Iterator iterator = currentAnswers.keys();
		while(iterator.hasNext()){
		    playerNames.add((String)iterator.next());
		}
		return playerNames;
	}
	
	public int numberOfPlayerAnswered() {
		return currentAnswers.length();
	}
	public JSONObject getCurrentAnswers() {
		return currentAnswers;
	}

	
}
