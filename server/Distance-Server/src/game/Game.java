package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import main.QuestionGenerator;
import main.Server;

public abstract class Game {
	private final float interuptTimer = 11000;
	private long startTime;
	protected Stack<Question> history;
	protected Stack<Question> upcoming;
	protected List<Player> players = new ArrayList<Player>();
	//private Server server;
	protected Unit unit;
	public String code;
	
	
	public Game(String code, Unit unit) {
		this.unit = unit;
		this.code = code;
	}
	
	public void join(Player player) {
		for (Player p : players) p.outputThread.newPlayerJoined(player.getNickname());
		players.add(player);
	}
	
	public void answer(Player player, Float answer) {
		
	}
	public Player getOwner() {
		return players.get(0);
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
