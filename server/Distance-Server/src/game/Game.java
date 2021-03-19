package game;

import java.util.Stack;

import main.QuestionGenerator;
import main.Server;

public abstract class Game {
	protected Stack<Question> history;
	protected Stack<Question> upcoming;
	//private Server server;
	protected Unit unit;
	
	public void join(String nickname, String code) {
		
	}
	
	public void answer(Player player, Float answer) {
		
	}
	
	public Game(Unit unit) {
		this.unit = unit;
	}

	public abstract void startGame();
}
