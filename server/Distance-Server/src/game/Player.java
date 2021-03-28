package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import main.InputThread;
import main.Main;
import main.OutputThread;

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
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}
	public void joinGame(Game game) {
		game.join(this);
		this.game=game;
	}
	public void startGame() {
		if (game.getOwner()==this) {
			game.startGame();
		}
	}

	public void disconect() {
		try {
			this.inputThread.disconnect();
			this.outputThread.disconnect();
			this.socket.close();
		} catch ( IOException e ) {
			//ignore
		}
	}
}
