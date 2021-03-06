package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import main.GameController;

public abstract class Game {
	protected Stack<Question> history = new Stack<>();
	protected Stack<Question> upcoming;
	protected List<Player> players = new ArrayList<Player>();
	protected Question currentQuestion;
	protected Unit unit;
	public String code;
	protected boolean gameHasStarted;
	
    
	private final static int maxTime = 30;
	private	ScheduledExecutorService executorService;
	
	
	public Game(String code, Unit unit, Player creator) {
		this.unit = unit;
		this.code = code;
		this.players.add(creator);
		System.out.println("Game  created by "+creator.getNickname()+ " with code: "+code);
		System.out.println("Sending game code to owner");

	}
	void sendQuestion() {
		try {
			currentQuestion = upcoming.pop();
			history.push(currentQuestion);
			System.out.println("Sending question to all players");
			for (Player p : players) p.outputThread.sendQuestion(currentQuestion.getQuestion().toString());
			startTimer();
		}catch(Exception e) {
			System.out.println("Det finnes ingen q");
		}
	}
	private void startTimer() {
		Runnable timer = () -> answerForRest();
		executorService = Executors.newSingleThreadScheduledExecutor();

		executorService.schedule(timer, maxTime+1, TimeUnit.SECONDS);

	}
	private void answerForRest() {
		ArrayList<String> playersWhoHaveAnswerd = currentQuestion.getPlayersWhoHaveAnswered();
		for (Player p : players) {
			if (!playersWhoHaveAnswerd.contains(p)) {
				this.answer(p, 0);
			}
		}
	}
	public void join(Player player) {
		for (Player p : players) p.outputThread.newPlayerJoined(player.getNickname());
		players.add(player);
		System.out.println(player.getNickname()+" joined game " + code);
	}
	
	public void answer(Player player, double answer) {
		
		System.out.println(player.getNickname() +" answered "+answer);
		currentQuestion.playerAnswered(player, answer);
		if (currentQuestion.numberOfPlayerAnswered()==players.size()) {
			System.out.println("All players have answerd");
			executorService.shutdownNow();
			sendResult();
		}
	}
	public void sendResult() {
		System.out.println("Sending result");
		try {
			JSONObject result = new JSONObject();
			result.put("Last", upcoming.empty());
			result.put("Answer", currentQuestion.getCorrectAnswer());
			result.put("Result", currentQuestion.getCurrentAnswers());
			for (Player p: players) p.outputThread.sendPartialResult(result.toString());
			if(upcoming.empty()) {
				System.out.println("Sending result to all players");
				for (Player p: players) p.outputThread.sendDisconnect();
				GameController.getInstance().endGame(code);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public Player getOwner() {
		return players.get(0);
	}
	public void leaveGame(Player player) {
		System.out.println(player.getNickname()+" is leaving game");
		for (int i=0;i<players.size();i++) {
			if(players.get(i)==player) {
				players.remove(i);
				if(players.size()==0) {
					GameController.getInstance().endGame(code);
					break;
				}
				if(i==0) {
					this.players.get(0).outputThread.sendYouAreOwner();	
				}
				for (Player p : players) p.outputThread.sendPlayerLeftGame(player.getNickname());
				break;
			}
		}
	}
	
	protected JSONObject getInfo() {
		JSONObject info = new JSONObject();
		try {
			info.put("players", players.stream().map(p->p.getNickname()).collect(Collectors.toList()) );
			info.put("unit", unit.name());
			
		}catch (JSONException e) {
			
		}
		return info;
	}
	
	public String getAllNames() {
		String r="";
		for (Player p : players) {
			r+=p.getNickname()+" \n";
		}
		return r;
	}
	public void startGame() {
		gameHasStarted = true;
	}
	public boolean isStarted() {
		return gameHasStarted;
		
	}
	public abstract String getGameInfo();
	

}

