package com.sigurvar.distanceduel.states;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.controller.GameController;
import com.sigurvar.distanceduel.game.models.GameModel;
import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.utility.StateController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JoinGameState extends ConnectToServerState {

    private String gameCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_state);
        StateController.getInstance().setState(this);
    }
    public void joinGame(View view){
        connectToServer();
        gameCode = ((EditText)findViewById(R.id.gameCode)).getText().toString();
        serverController.outputThread.sendGameCode(gameCode);
    }

    public void joinedGameSuccessful(String info){
        // TODO: info needs to contain game setting and players in game
        try{
            JSONObject details = new JSONObject(info);
            GameModel g = Game.getInstance().setupNewGame(nickname, gameCode, details.getInt("mode"), getApplicationContext());
            JSONArray players = details.getJSONArray("players");
            for (int i=0;i<players.length();i++){
                g.addNewPlayer(players.get(i).toString());
            }
        }catch (JSONException ignored){

        }
        Intent intent = new Intent(this, LobbyState.class);
        this.startActivity(intent);
    }

    public void nicknameAlreadyTaken(){
        //TODO: error melding
        System.out.println("nicknameAlreadyTaken");
    }
    public void gameCodeDoesNotExist(){
        //TODO: error melding
        System.out.println("gameCodeDoesNotExist");
    }
    public void gameAlreadyStarted(){
        //TODO: error melding
        System.out.println("gameAlreadyStarted");
    }
}