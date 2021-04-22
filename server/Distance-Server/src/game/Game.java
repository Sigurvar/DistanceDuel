package game;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import main.GameController;
import main.Main;
import main.QuestionGenerator;
import main.Server;

public abstract class Game {
	private int id;
	protected Stack<Question> history = new Stack<>();
	protected Stack<Question> upcoming;
	protected List<Player> players = new ArrayList<Player>();
	protected Question currentQuestion;
	//private Server server;
	protected Unit unit;
	private JSONObject currentAnswer;
	private List<Player> playersWhoHaveAnswerd;
	public String code;
	private boolean gameHasStarted;
	
	
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
			playersWhoHaveAnswerd = new ArrayList<>();
			currentQuestion = upcoming.pop();
			history.push(currentQuestion);
			currentAnswer = new JSONObject();
			System.out.println("Sending question to all players");
			String questionString = "How many " + currentQuestion.getUnit() + " is it between " + currentQuestion.getPlaceA() + " and " + currentQuestion.getPlaceB() + "?";
			for (Player p : players) p.outputThread.sendQuestion(questionString);
			startTimer();
		}catch(Exception e) {
			System.out.println("Det finnes ingen q");
		}
	}
	private void startTimer() {
		Runnable timer = () -> answerForRest();
		ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

		executorService.schedule(timer, 12, TimeUnit.SECONDS);
	}
	private void answerForRest() {
		for (Player p : players) {
			System.out.println(p.getNickname());
			if (!playersWhoHaveAnswerd.contains(p)) {
				this.answer(p, 0);
			}
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
			playersWhoHaveAnswerd.add(player);
			this.currentAnswer.append(player.getNickname(), jPlayer);
			System.out.println(currentAnswer);
			if (this.currentAnswer.length()==players.size()) {
				System.out.println("All players have answerd");
				sendResult();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public void sendResult() {
		System.out.println("Sending result");
		try {
			JSONObject result = new JSONObject();
			result.put("Last", upcoming.empty());
			result.put("Answer", currentQuestion.getCorrectAnswer());
			result.put("Result", currentAnswer);
			for (Player p: players) p.outputThread.sendPartialResult(result.toString());
			if(upcoming.empty()) {//TODO må endres hvis man også skal lage flere spm
				System.out.println("Done");
				for (Player p: players) p.outputThread.sendDisconnect();
				GameController.getInstance().endGame(id, code);
			}
		} catch (JSONException e) {
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

