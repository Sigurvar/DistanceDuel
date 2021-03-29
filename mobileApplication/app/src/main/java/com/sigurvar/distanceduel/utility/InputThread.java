package com.sigurvar.distanceduel.utility;

import android.util.Log;

import java.io.DataInputStream;
import java.io.IOException;

public class InputThread extends Thread{

    /** Message codes on messages received from server **/
    private static final int PLAYERS_IN_GAME = 1;
    private static final int GAME_CODE = 2;
    private static final int GAME_CODE_DOES_NOT_EXIST = 3;
    private static final int NEW_PLAYER_IN_GAME = 4;
    private static final int NEW_QUESTION = 5;
    private static final int PARTIAL_RESULT = 6;
    private static final int GAME_DONE = 7;
    private static final int YOU_ARE_OWNER = 8;//Veldig usikker på navnet her
    private static final int GAME_STARTING_SOON = 9;//Burde det være countdown mellom hvert spm

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
                    case PLAYERS_IN_GAME:
                        Log.i("InputThread", "Players in game: " + message);
                        this.setDisplayInfo("Players in game: " + message);
                        break;
                    case GAME_CODE:
                        Log.i("InputThread", "Game code received: " + message);
                        this.setDisplayInfo("Game code received: " + message);
                        break;
                    case GAME_CODE_DOES_NOT_EXIST:
                        Log.i("InputThread", message);
                        this.setDisplayInfo(message);
                        break;
                    case NEW_PLAYER_IN_GAME:
                        Log.i("InputThread", message + " joined the game");
                        this.setDisplayInfo(message + " joined the game");
                        break;
                    case NEW_QUESTION:/*
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        ServerController serverController = ServerController.getInstance();
                                        serverController.answerToSlow();
                                    }
                                },
                                10000
                        );*/
                        Log.i("InputThread", "Received question: " + message);
                        this.setDisplayInfo("Question: "+message);
                        break;
                    case PARTIAL_RESULT:
                        Log.i("InputThread", "Received result: " + message);
                        break;
                    case GAME_DONE:
                        Log.i("InputThread", "Game done: " + message);
                        this.disconnect();
                        break;
                    case YOU_ARE_OWNER:
                        // TODO: implement function to set player as owner
                        Log.i("InputThread", message);
                        this.setDisplayInfo(message);
                        break;
                }
                // TODO: Insert logic which use the recived message (textMessage)
            } catch (IOException e1) {
                e1.printStackTrace();
                return;
            }
        }
    }
    private void setDisplayInfo(String text){
        serverController.main.displayInfo(text);
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
