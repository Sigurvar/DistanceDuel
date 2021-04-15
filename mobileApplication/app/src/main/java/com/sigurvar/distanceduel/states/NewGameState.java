package com.sigurvar.distanceduel.states;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.utility.StateController;

public class NewGameState extends ConnectToServerState {

    private String settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_state);
        StateController.getInstance().setState(this);
    }
    public void createGame(View view) {
        Log.i("Game", "Starting new game");
        connectToServer();
        settings = "Dette er innstilligene";
        serverController.outputThread.sendNewGame("Dette er innstilligene");
    }

    public void receivedGameCode(String gameCode){
        Game.getInstance().setupNewGame(nickname, gameCode, getApplicationContext());
        Game.getInstance().getGameModel().setAsHost();
        Intent intent = new Intent(this, LobbyState.class);
        this.startActivity(intent);
    }
}