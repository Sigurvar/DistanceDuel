package com.sigurvar.distanceduel.utility;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;

public class OutputThread extends Thread{
    /** Message codes on messages sent to server **/
    private static final int SET_NICKNAME = 1;
    private static final int CREATE_NEW_GAME = 2;
    private static final int JOIN_GAME = 3;
    private static final int START_GAME = 4;
    private static final int ANSWER_QUESTION = 5;
    private static final int LEAVING_GAME = 6;
    private static final int NEXT_QUESTION = 7;
    private static final int CREATED_QUESTION = 8;



    private final DataOutputStream dataOutputStream;

    public OutputThread(DataOutputStream dataOutputStream){
        this.dataOutputStream = dataOutputStream;
    }

    public void sendNickname(String nickname){
        this.sendData(SET_NICKNAME, nickname);
    }
    public void sendNewGame(String setting){
        this.sendData(CREATE_NEW_GAME, setting);
    }
    public void sendGameCode(String code){
        this.sendData(JOIN_GAME, code);
    }
    public void sendAnswer(String answer){
        this.sendData(ANSWER_QUESTION, answer);
    }
    public void sendStartGame(){
        this.sendData(START_GAME, "");
    }
    public void leaveGame() { this.sendData(LEAVING_GAME, "");}
    public void getNextQuestion(){this.sendData(NEXT_QUESTION, "");}
    public void sendCreatedQuestion(String question){
        this.sendData(CREATED_QUESTION, question);
    }




    private void sendData(int message_type, String message) {
        Log.i("SendingThread","Sending message with type "+message_type);

        try {
            dataOutputStream.writeByte(message_type);
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
