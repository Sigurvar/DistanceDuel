package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import main.QuestionGenerator;
import main.Server;

public abstract class Game {
	protected Stack<Question> history;
	protected Stack<Question> upcoming;
	protected List<Player> players = new ArrayList<Player>();
	//private Server server;
	protected Unit unit;
	private String code;
	
	public boolean join(String code, Player player) {
		System.out.println("Checking code");
		System.out.println(code);
		System.out.println(this.code);
		if (!this.code.contentEquals(code)) {return false;}
		System.out.println("Failed");
		for (Player p : players) p.newPlayerJoined(player.getNickname());
		players.add(player);
		return true;
	}
	
	public void answer(Player player, Float answer) {
		
	}
	
	public Game(String code, Unit unit) {
		this.unit = unit;
		this.code = code;
		System.out.println(this);
	}
	public String getAllNames() {
		String r="";
		for (Player p : players) {
			r+=p.getNickname()+" \n";
		}
		return r;
	}

	public abstract void startGame();
	
}
