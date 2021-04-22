package main;

import java.io.DataOutputStream;
import java.io.IOException;

public class OutputThread extends Thread{
	/** Message codes on messages sent to client **/
    private static final int GAME_INFO = 1;
    private static final int GAME_CODE = 2;
    private static final int GAME_CODE_DOES_NOT_EXIST = 3; 
    private static final int NEW_PLAYER_IN_GAME = 4;
    private static final int NEW_QUESTION = 5;
    private static final int RESULT = 6;
    private static final int YOU_ARE_OWNER = 7;//Veldig usikker på navnet her
    private static final int GAME_STARTING_SOON = 9;//Burde det være countdown mellom hvert spm
    private static final int PLAYER_LEFT_GAME = 10;
    private static final int CREATE_QUESTION = 11;
    private static final int DISCONNECT = 12;
    private static final int NICKNAME_ALREADY_TAKEN = 13;
    

	private final DataOutputStream dataOutputStream;

    public OutputThread(DataOutputStream dataOutputStream){
        this.dataOutputStream = dataOutputStream;
    }
    
    public void sendGameInfo(String players) {
		this.sendData(GAME_INFO, players);
	}	
	public void sendGameCode(String code) {
		this.sendData(GAME_CODE,code);
	}
	public void sendGameCodeDoesNotExist() {
		this.sendData(GAME_CODE_DOES_NOT_EXIST);
	}
	public void newPlayerJoined(String nickname) {
		this.sendData(NEW_PLAYER_IN_GAME, nickname);
	}
	public void sendQuestion(String question) {
		this.sendData(NEW_QUESTION, question);
	}
	public void sendPartialResult(String result) {
		this.sendData(RESULT, result);
	}
	public void sendYouAreOwner() {
		this.sendData(YOU_ARE_OWNER);
	}
	public void sendPlayerLeftGame(String nickname) {
		this.sendData(PLAYER_LEFT_GAME, nickname);
	}
	public void sendCreateQuestion() {
		this.sendData(CREATE_QUESTION);
	}
	public void sendDisconnect() {
		this.sendData(DISCONNECT);
	}
	public void sendNicknameAlreadyTaken() {
		this.sendData(NICKNAME_ALREADY_TAKEN);
	}
	
	private void sendData(int message_type, String message) {
		try {
			dataOutputStream.writeByte(message_type);
			dataOutputStream.writeUTF(message);
			dataOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
	}
	private void sendData(int message_type) {
		this.sendData(message_type, "");
	}
	public void disconnect(){
        try {
            this.dataOutputStream.close();
            super.interrupt();
        } catch ( IOException e ) {
            //ignore
        }
    }
}
