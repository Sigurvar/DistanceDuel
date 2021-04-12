package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.controller.GameController;
import com.sigurvar.distanceduel.game.views.ReceiveQuestionState;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;


public class LobbyState extends ReceiveQuestionState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_state);
        StateController.getInstance().setState(this);
    }
    public void canStartGame(){
        findViewById(R.id.startGame).setVisibility(View.VISIBLE);
    }

    public void setPlayersInGame(String players){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = findViewById(R.id.players);
                tv.setText(players);
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
}