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
    private static final int LEAVING_GAME = 6; //TODO implementer håndtering
    
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
		                System.out.println("Player setting nickname to: " + data);
		                break;
		            case CREATE_NEW_GAME:
		            	// Create new game with code and send to user
		            	// TODO exception no game handeling
		            	Game game = Main.getInstance().createGame(this.player);
		            	this.player.joinGame(game);
		            	player.outputThread.sendGameCode(game.code);
		            	break;
		            case JOIN_GAME:
		            	Game g = Main.getInstance().joinGame(data, player);
		            	System.out.println(player.getNickname()+" joining with game code: " + data);
		            	if (g==null) {
		            		player.outputThread.sendGameCodeDoesNotExist();
		            	}else {
		            		System.out.println(player.getNickname()+" joining with game code: " + data);
			            	player.joinGame(g);
			            	player.outputThread.sendPlayersInGame(g.getAllNames());
		            	}
		            	break;
		            case START_GAME:
		            	player.startGame();
		            	System.out.println("Starting game");
		            	break;
		            case ANSWER_QUESTION:
		            	System.out.println(player.getNickname() + " answerd: " + data);
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
