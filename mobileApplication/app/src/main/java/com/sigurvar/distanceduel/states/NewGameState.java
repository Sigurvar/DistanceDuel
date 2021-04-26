package com.sigurvar.distanceduel.states;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.utility.StateController;

import org.json.JSONException;
import org.json.JSONObject;

public class NewGameState extends ConnectToServerState {

    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_state);
        StateController.getInstance().setState(this);
    }
    public void createGame(View view) {
        if(view.getId()==R.id.normalMode){
            gameMode = Game.NORMAL_MODE;
        }else {
            gameMode = Game.WRITE_QUESTION_MODE;
        }
        connectToServer();
        try{

            JSONObject settings = new JSONObject();
            settings.put("gameMode",gameMode);
            serverController.outputThread.sendNewGame(settings.toString());
        }catch (JSONException e){
            displayErrorMessage((String)getText(R.string.error_could_not_create_game));
        }
    }

    public void receivedGameCode(String gameCode){
        Game.getInstance().setupNewGame(nickname, gameCode, gameMode, getApplicationContext());
        Game.getInstance().getGameModel().setAsHost();
        Intent intent = new Intent(this, LobbyState.class);
        this.startActivity(intent);
    }
}