package game;

import java.util.ArrayList;

public class Question {
	
	private String placeA;
	private String placeB;
	private float answer;
	private Unit unit;
	private ArrayList<Float> answers;
	
	public Question(String placeA, String placeB, float answer, Unit unit) {
		this.placeA = placeA;
		this.placeB = placeB;
		this.answer = answer;
		this.unit = unit;
	}

	// TODO: burde vel gi poeng basert på hvor nære man er
	public boolean checkAnswer(float answer) {
		if (answer == this.answer/unit.getLength()) {
			return true;
		}
		return false;
	}
}
