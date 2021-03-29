package game;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONObject;

import main.Main;
import main.QuestionGenerator;
import main.Server;

public abstract class Game {
	private final float interuptTimer = 11000;
	private long startTime;
	private int id;
	protected Stack<Question> history;
	protected Stack<Question> upcoming;
	protected List<Player> players = new ArrayList<Player>();
	//private Server server;
	protected Unit unit;
	private JSONObject currentAnswer;
	public String code;
	
	
	public Game(String code, Unit unit, int id, Player creator) {
		this.unit = unit;
		this.code = code;
		this.id= id;
		this.players.add(creator);
		System.out.println("Game "+id+" created by "+creator.getNickname()+ " with code "+code);
	}
	void sendQuestion() {
		try {
			Question question = upcoming.pop();
		}catch(EmptyStackException e) {
			System.out.println("Det finnes ingen q");
		}
		currentAnswer = new JSONObject();
		System.out.println("Sending question to all players");
		for (Player p : players) p.outputThread.sendQuestion("Hvor mange bananer er det mellom oslo og bergen ");
		
	}
	public void join(Player player) {
		for (Player p : players) p.outputThread.newPlayerJoined(player.getNickname());
		players.add(player);
		System.out.println(player.getNickname()+" joined game "+id);
	}
	
	public void answer(Player player, Float answer) {
		// TODO add timer on question
		System.out.println(player.getNickname() +" answered "+answer);
		try {
			this.currentAnswer.append(player.getNickname(), answer);
			if (this.currentAnswer.length()==players.size()) {
				System.out.println("All players have answerd");
				
				for (Player p: players) p.outputThread.sendPartialResult(currentAnswer.toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Player getOwner() {
		return players.get(0);
	}
	public void leaveGame(Player player) {
		System.out.println(player.getNickname()+" is leaving the game "+id);
		for (int i=0;i<players.size();i++) {
			if(players.get(i)==player) {
				System.out.println(i);
				players.remove(i);
				if(players.size()==0) {
					Main main = Main.getInstance();
					main.endGame(id, code);
				}
				else if(i==0) {
					this.players.get(0).outputThread.sendYouAreOwner();
				}
				return;
			}
		}
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
