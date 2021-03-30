package game;

import main.QuestionGenerator;

public class NormalMode extends Game {

	public NormalMode(String code, Unit unit, int id, Player creator) {
		super(code, unit, id, creator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame() {
		upcoming = QuestionGenerator.generate(unit, 10);
		super.sendQuestion();
	}

}
