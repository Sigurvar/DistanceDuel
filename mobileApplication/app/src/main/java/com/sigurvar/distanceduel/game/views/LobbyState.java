package com.sigurvar.distanceduel.game.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;


public class LobbyState extends ReceiveQuestionState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_state);
        StateController.getInstance().setState(this);
        if (gameModel.isHost()) canStartGame();
        setPlayersInGame();
        setGameCode();
    }
    public void canStartGame(){
        findViewById(R.id.startGame).setVisibility(View.VISIBLE);
    }
    private void setGameCode(){
        ((TextView)findViewById(R.id.gameCode)).setText("Game code is: "+gameModel.getGameCode());
    }

    public void setPlayersInGame(){
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                TextView tv = findViewById(R.id.players);
                tv.setText(gameModel.getAllPlayersInGame());
            }
        });

    }
    public void startGame(View view){
        ServerController.getInstance().outputThread.sendStartGame();
    }

    public void displayInfo(String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = findViewById(R.id.displayInfo);
                tv.setText(text);
            }
        });
    }
    @Override
    public void onDestroy() {
        ServerController serverController = ServerController.getInstance();
        if (serverController.getIsConnected()){
            serverController.outputThread.leaveGame();
            serverController.disconnect();
        }
        super.onDestroy();
    }

}