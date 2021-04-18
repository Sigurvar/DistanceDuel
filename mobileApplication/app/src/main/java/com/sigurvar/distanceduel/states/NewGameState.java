package com.sigurvar.distanceduel.states;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.utility.StateController;

import org.json.JSONException;
import org.json.JSONObject;

public class NewGameState extends ConnectToServerState {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_state);
        StateController.getInstance().setState(this);
    }
    public void createGame(View view) {
        Log.i("Game", "Starting new game");
        int gameMode;
        if(view.getId()==R.id.normalMode){
            gameMode = NORMAL_MODE;
        }else {
            gameMode = WRITE_QUESTION_MODE;
        }
        connectToServer();
        try{
            // TODO: add other settings posibilities
            JSONObject settings = new JSONObject();
            settings.put("gameMode",gameMode);
            serverController.outputThread.sendNewGame(settings.toString());
        }catch (JSONException e){
            //TODO: could not create game
        }
    }

    public void receivedGameCode(String gameCode){
        Game.getInstance().setupNewGame(nickname, gameCode, getApplicationContext());
        Game.getInstance().getGameModel().setAsHost();
        Intent intent = new Intent(this, LobbyState.class);
        this.startActivity(intent);
    }
}