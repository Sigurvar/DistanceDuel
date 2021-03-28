package game;

import main.QuestionGenerator;

public class NormalMode extends Game {

	public NormalMode(String code, Unit unit) {
		super(code, unit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startGame() {
		upcoming = QuestionGenerator.generate(unit, 10);
		for (Player p : players) p.outputThread.sendQuestion("Dette er spm 1 !!!!!");
	}

}
