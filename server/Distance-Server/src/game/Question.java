package game;

import java.util.ArrayList;

public class Question {
	
	private String placeA;
	private String placeB;
	private double correctAnswer;
	private Unit unit;
	private ArrayList<Double> answers;
	private final double maxScore = 1500;
	
	public Question(String placeA, String placeB, double correctAnswer, Unit unit) {
		this.placeA = placeA;
		this.placeB = placeB;
		this.correctAnswer = correctAnswer;
		this.unit = unit;
	}

	public long calculateScore(double userAnswer) {
		double percentageIncorrect = Math.abs(1 - Math.min((userAnswer / correctAnswer), 2));
		long score = Math.round(maxScore - (maxScore * percentageIncorrect));
		System.out.println(score);
		return score;
			
	}

public String getPlaceA() {
	return placeA;
}


public String getPlaceB() {
	return placeB;
}


public double getCorrectAnswer() {
	return correctAnswer;
}


public Unit getUnit() {
	return unit;
}

	
}
