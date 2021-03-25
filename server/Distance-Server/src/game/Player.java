package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import main.Main;

public class Player extends Thread{
	
	private final float interuptTimer = 11000;
	private long startTime;
	private String nickname;
	private int id;
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private boolean shouldInterupt;

	public Player(Socket socket, int id) {

		this.socket=socket;
		this.id=id;
		try {
			this.outputStream = new DataOutputStream(this.socket.getOutputStream());
            this.inputStream = new DataInputStream(this.socket.getInputStream());
    		start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNickname() {
		return nickname;
	}

	@Override
	public void run() {

		byte messageType;
		while(!this.isInterrupted()) {
			System.out.println("Waiting");
			try {
				while(inputStream.available() != 0) {
					if (shouldInterupt && this.startTime-System.currentTimeMillis()>this.interuptTimer) {
						System.out.println(nickname + " did not answer");
						throw new RuntimeException("User did not anser");
					}
				}
				System.out.println("Recieved message");
				messageType = inputStream.readByte();
				String data = inputStream.readUTF();
		        switch(messageType)
		        {
		            case 1: // Nickname
		            	nickname= data;
		                System.out.println("Player "+id+" setting nickname to: " + data);
		                break;
		            case 2: // New game
		            	System.out.println("Creating new game; "+ data);
		            	// Create new game with code and send to user
		            	Main.getInstance().createGame("1234", this);
		            	this.sendGameCode("1234");
		            	return;
		            case 3: // Game code
		            	System.out.println(nickname+" joining with game code: " + data);
		            	Game g = Main.getInstance().joinGame(data, this);
		            	this.sendPlayersInGame(g.getAllNames());
		            	return;
		            case 4: // Answer
		            	System.out.println(nickname + " answerd: " + data);
		            	break;
		            case 5: //Start game
		            	System.out.println("Starting game");
		            	break;
		        }
			} catch (IOException e) {
				e.printStackTrace();
				this.disconect();
				return;
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			shouldInterupt = false;
		}
	}
		
	public void sendPlayersInGame(String players) {
		this.sendData(1, players);
	}	
	public void sendGameCode(String code) {
		this.sendData(2,code);
	}
	public void newPlayerJoined(String nickname) {
		this.sendData(3, nickname);
	}
	public void sendQuestion(String question) {
		this.shouldInterupt = true;
		this.startTime = System.currentTimeMillis();	
		this.sendData(4, question);
	}
	public void sendResult(String result) {
		this.sendData(5, result);
	}
	public void sendGameDone(String done) {
		this.sendData(6, done);
	}
	
	
	private void sendData(int type, String message) {
		System.out.println("sending message: " +message);
		try {
			outputStream.writeByte(type);
			outputStream.writeUTF(message);
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	public void disconect() {
		try {
			this.inputStream.close();
			this.outputStream.close();
			this.socket.close();
		} catch ( IOException e ) {
			//ignore
		}
	}

}
