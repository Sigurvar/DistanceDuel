package game;

import main.QuestionGenerator;

public class NormalMode extends Game {

	public NormalMode(Unit unit) {
		super(unit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame() {
		upcoming = QuestionGenerator.generate(unit, 10);
		
	}

}
