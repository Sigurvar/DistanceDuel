package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.stream.Collectors;

import main.GameController;
import main.InputThread;
import main.OutputThread;
import main.Server;

public class Player{
	
	private String nickname;
	private int id;
	private Socket socket;
	private Game game;
	public OutputThread outputThread;
    public InputThread inputThread;

	public Player(Socket socket, int id) {
		this.socket=socket;
		this.id=id;
		try {
			this.outputThread = new OutputThread(new DataOutputStream(this.socket.getOutputStream()));
			this.inputThread = new InputThread(new DataInputStream(this.socket.getInputStream()), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setNickname(String nickname) {
		 System.out.println("Player " +id +" seting nickname to: " + nickname);
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	public int getId() {
		return id;
	}
	public void answerQuestion(String answer) {
		double ans = -1;
		try {
			ans = Double.valueOf(answer);
		}catch(NumberFormatException e) {}
		this.game.answer(this, ans);
		
	}
	public void createGame(String settings) {
		Game game = GameController.getInstance().createGame(this, settings);
		this.game = game;
    	this.outputThread.sendGameCode(game.code);
	}
	public void joinGame(String gameCode) {
		Game g = GameController.getInstance().joinGame(gameCode, this);
    	System.out.print(this.getNickname()+" wants to join game with game code: " + gameCode+"...");
    	if (g==null) {
    		this.outputThread.sendGameCodeDoesNotExist();
    		System.out.println(" Game code does not exist");
    	}else {
    		if (g.players.stream().map(p->p.getNickname()).collect(Collectors.toList()).contains(nickname)) {
    			System.out.println(" Nickname already taken");
        		this.outputThread.sendNicknameAlreadyTaken();
    		}
    		else if(g.isStarted()) {
    			System.out.println(" Game already started");
        		this.outputThread.sendGameAlreadyStarted();
    		}
    		else {
    			System.out.println(" Sucess");
    			this.outputThread.sendGameInfo(g.getGameInfo());
            	g.join(this);
        		this.game=g;
    		}
    		
    		
    	}
	}
	public void startGame() {
		System.out.print("Player requesting to start game... ");
		if (game.getOwner()==this) {
			System.out.println("Approved, starting game");
			game.startGame();
		}else {
			System.out.println("Not owner");
		}
	}
	public void askForNextQuestion() {
		if (game.getOwner()==this) {
			game.sendQuestion();
		}
	}
	
	public void createdQuestion(String question) {
		if(game instanceof ChallengeMode) {
			((ChallengeMode) game).userGeneratedQuestion(this, question);
		}
	}

	public void disconect() {
		try {
			if (game!=null) {
				game.leaveGame(this);
			}
			Server.getInstance().disconnectPlayer(this);
			this.inputThread.disconnect();
			this.outputThread.disconnect();
			this.socket.close();
		} catch ( IOException e ) {
			System.out.println(e);
			//ignore
		}
	}
}
