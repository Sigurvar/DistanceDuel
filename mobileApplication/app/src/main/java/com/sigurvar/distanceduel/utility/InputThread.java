package com.sigurvar.distanceduel.utility;

import android.util.Log;

import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.controller.ChallengeController;
import com.sigurvar.distanceduel.states.JoinGameState;
import com.sigurvar.distanceduel.states.NewGameState;

import java.io.DataInputStream;
import java.io.IOException;

public class InputThread extends Thread{

    /** Message codes on messages received from server **/
    private static final int GAME_INFO = 1;
    private static final int GAME_CODE = 2;
    private static final int GAME_CODE_DOES_NOT_EXIST = 3;
    private static final int NEW_PLAYER_IN_GAME = 4;
    private static final int NEW_QUESTION = 5;
    private static final int RESULT = 6;
    private static final int YOU_ARE_OWNER = 7;
    private static final int PLAYER_LEFT_GAME = 10;
    private static final int CREATE_QUESTION = 11;
    private static final int DISCONNECT = 12;
    private static final int NICKNAME_ALREADY_TAKEN = 13;
    private static final int GAME_ALREADY_STARTED = 14;

    private final ServerController serverController;
    private final DataInputStream dataInputStream;

    public InputThread(DataInputStream dataInputStream, ServerController serverController){
        this.dataInputStream = dataInputStream;
        this.serverController = serverController;
        this.start();
    }
    @Override
    public void run(){
        while(!this.isInterrupted()) {
            try {
                byte messageType = dataInputStream.readByte();
                String message = dataInputStream.readUTF();
                switch (messageType) {
                    case GAME_INFO:
                        Log.i("InputThread", "Players in game: " + message);
                        ((JoinGameState)StateController.getInstance().getState()).joinedGameSuccessful(message);
                        break;
                    case GAME_CODE:
                        Log.i("InputThread", "Game code received: " + message);
                        ((NewGameState)StateController.getInstance().getState()).receivedGameCode(message);
                        break;
                    case GAME_CODE_DOES_NOT_EXIST:
                        Log.i("InputThread", "Game code does not exist");
                        ((JoinGameState)StateController.getInstance().getState()).gameCodeDoesNotExist();
                        break;
                    case NEW_PLAYER_IN_GAME:
                        Log.i("InputThread", message + " joined the game");
                        Game.getInstance().getGameController().newPlayerJoinedGame(message);
                        break;
                    case NEW_QUESTION:
                        Game.getInstance().getGameController().receivedQuestion(message);
                        Log.i("InputThread", "Received question: " + message);
                        break;
                    case RESULT:
                        Log.i("InputThread", "Received result: " + message);
                        Game.getInstance().getGameController().receivedResult(message);
                        break;
                    case YOU_ARE_OWNER:
                        Log.i("InputThread", message);
                        Game.getInstance().getGameController().youAreOwner();
                        break;
                    case PLAYER_LEFT_GAME:
                        Game.getInstance().getGameController().playerLeftTheGame(message);
                        Log.i("InputThread", message + " left the game");
                        break;
                    case CREATE_QUESTION:
                        Log.i("InputThread", "Create question");
                        ((ChallengeController)Game.getInstance().getGameController()).createQuestion();
                        break;
                    case DISCONNECT:
                        Log.i("InputThread", "Disconnecting");
                        serverController.disconnect();
                        break;
                    case NICKNAME_ALREADY_TAKEN:
                        Log.i("InputThread", "Nickname already taken");
                        ((JoinGameState)StateController.getInstance().getState()).nicknameAlreadyTaken();
                        break;
                    case GAME_ALREADY_STARTED:
                        Log.i("InputThread", "Game already started");
                        ((JoinGameState)StateController.getInstance().getState()).gameAlreadyStarted();
                        break;
                }
            } catch (IOException e1) {
                return;
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
