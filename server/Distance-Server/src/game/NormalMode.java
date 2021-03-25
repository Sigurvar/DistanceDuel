package game;

import java.io.FileNotFoundException;

import main.QuestionGenerator;

public class NormalMode extends Game {

	public NormalMode(Unit unit) {
		super(unit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame() {
		QuestionGenerator questionGenerator = new QuestionGenerator();
		try {
			upcoming = questionGenerator.generate(unit, 10);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
