package game;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import main.GameController;
import main.Main;
import main.QuestionGenerator;
import main.Server;

public abstract class Game {
	private final float interuptTimer = 11000;
	private long startTime;
	private int id;
	protected Stack<Question> history = new Stack<>();
	protected Stack<Question> upcoming;
	protected List<Player> players = new ArrayList<Player>();
	protected Question currentQuestion;
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
		System.out.println("Sending game code to owner");
	}
	void sendQuestion() {
		try {
			currentQuestion = upcoming.pop();
			history.push(currentQuestion);
			currentAnswer = new JSONObject();
			System.out.println("Sending question to all players");
			String questionString = "How many " + currentQuestion.getUnit() + " is it between " + currentQuestion.getPlaceA() + " and " + currentQuestion.getPlaceB() + "?";
			for (Player p : players) p.outputThread.sendQuestion(questionString);
		}catch(Exception e) {
			System.out.println("Det finnes ingen q");
			//TODO send game over
		}
		
		
	}
	public void join(Player player) {
		for (Player p : players) p.outputThread.newPlayerJoined(player.getNickname());
		players.add(player);
		System.out.println(player.getNickname()+" joined game "+id);
	}
	
	public void answer(Player player, double answer) {
		
		System.out.println(player.getNickname() +" answered "+answer);
		try {
			JSONObject jPlayer = new JSONObject();
			jPlayer.put("Answer", answer);
			jPlayer.put("Score", currentQuestion.calculateScore((float)answer));

			this.currentAnswer.append(player.getNickname(), jPlayer);
			System.out.println(currentAnswer);
			if (this.currentAnswer.length()==players.size()) {
				System.out.println("All players have answerd");
				sendResult();
				//for (Player p: players) p.outputThread.sendPartialResult(currentAnswer.toString());
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public void sendResult() {
		
		try {
			JSONObject result = new JSONObject();
			result.put("Last", upcoming.empty());
			result.put("Answer", currentQuestion.getCorrectAnswer());
			result.put("Result", currentAnswer);
			for (Player p: players) p.outputThread.sendPartialResult(result.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Player getOwner() {
		return players.get(0);
	}
	public void leaveGame(Player player) {
		System.out.println(player.getNickname()+" is leaving game "+id);
		for (int i=0;i<players.size();i++) {
			if(players.get(i)==player) {
				players.remove(i);
				if(players.size()==0) {
					GameController.getInstance().endGame(id, code);
				}
				else if(i==0) {
					this.players.get(0).outputThread.sendYouAreOwner();	
				}
				for (Player p : players) p.outputThread.sendPlayerLeftGame(player.getNickname());
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
	public void gameComplete() {
		System.out.println("Game complete");
		for (Player p: players) p.outputThread.sendFinalResult("Game completed");
	}
	public abstract void startGame();
	

}

