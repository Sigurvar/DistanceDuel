package game;

import java.util.ArrayList;

public class Question {
	
	private String placeA;
	private String placeB;
	private float correctAnswer;
	private Unit unit;
	private ArrayList<Float> answers;
	private final float maxScore = 1500;
	
	public Question(String placeA, String placeB, float correctAnswer, Unit unit) {
		this.placeA = placeA;
		this.placeB = placeB;
		this.correctAnswer = correctAnswer;
		this.unit = unit;
	}

	public int calculateScore(float userAnswer) {
		float percentageIncorrect = Math.abs(1 - Math.min((userAnswer / correctAnswer), 2));
		int score = Math.round(maxScore - (maxScore * percentageIncorrect));
		System.out.println(score);
		return score;
			
	}

public String getPlaceA() {
	return placeA;
}


public String getPlaceB() {
	return placeB;
}


public float getCorrectAnswer() {
	return correctAnswer;
}


public Unit getUnit() {
	return unit;
}

	
}
