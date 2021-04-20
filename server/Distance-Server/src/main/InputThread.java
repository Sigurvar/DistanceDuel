package main;

import java.io.DataInputStream;
import java.io.IOException;

import game.Game;
import game.Player;

public class InputThread extends Thread{
    /** Message codes on messages received from client **/
    private static final int SET_NICKNAME = 1;
    private static final int CREATE_NEW_GAME = 2;
    private static final int JOIN_GAME = 3;
    private static final int START_GAME = 4;
    private static final int ANSWER_QUESTION = 5;
    private static final int LEAVING_GAME = 6; 
    private static final int NEXT_QUESTION = 7;
    private static final int CREATED_QUESTION = 8;
    
	private final Player player;
    private final DataInputStream dataInputStream;

    public InputThread(DataInputStream dataInputStream, Player player){
        this.dataInputStream = dataInputStream;
        this.player = player;
        this.start();
    }
	@Override
	public void run() {

		byte messageType;
		while(!this.isInterrupted()) {
			try {
				messageType = dataInputStream.readByte();
				String data = dataInputStream.readUTF();
		        switch(messageType)
		        {
		            case SET_NICKNAME:
		            	player.setNickname(data);
		                break;
		            case CREATE_NEW_GAME:
		            	player.createGame(data);
		            	break;
		            case JOIN_GAME:
		            	player.joinGame(data);
		            	break;
		            case START_GAME:
		            	player.startGame();
		            	break;
		            case ANSWER_QUESTION:
		            	player.answerQuestion(data);
		            	break;
		            case LEAVING_GAME:
		            	player.disconect();
		            	break;
		            case NEXT_QUESTION:
		            	player.askForNextQuestion();
		            	break;
		            case CREATED_QUESTION:
		            	player.createdQuestion(data);
		            	break;
		        }
			} catch (IOException e) {
				e.printStackTrace();
				this.disconnect();
				return;
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
	}
	public void disconnect(){
        try {
            this.dataInputStream.close();
            super.interrupt();
        } catch ( IOException e ) {
            //ignore
        }
    }
}
