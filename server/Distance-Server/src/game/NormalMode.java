package game;

import java.io.FileNotFoundException;

import org.json.JSONException;

import main.QuestionGenerator;

public class NormalMode extends Game {

	public NormalMode(String code, Unit unit, int id, Player creator) {
		super(code, unit, id, creator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame() {
		QuestionGenerator questionGenerator = new QuestionGenerator();
		try {
			upcoming = questionGenerator.generate(unit, 2);
		} catch (FileNotFoundException | JSONException e) {
			e.printStackTrace();
		}
		super.sendQuestion();
	}

}
