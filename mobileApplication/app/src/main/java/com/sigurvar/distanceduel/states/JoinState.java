package com.sigurvar.distanceduel.states;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.controller.GameController;
import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

public class JoinState extends State {

    private ServerController serverController = ServerController.getInstance();
    private String gameCode;
    private String nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_state);
        StateController.getInstance().setState(this);
    }
    public void joinGame(View view){
        setNickname(view);
        gameCode = ((EditText)findViewById(R.id.gameCode)).getText().toString();
        serverController.outputThread.sendGameCode(gameCode);
    }

    public void joinedGameSuccessful(String info){
        // TODO: info needs to contain game setting and players in game
        Game.getInstance().setupNewGame(nickname, gameCode, getApplicationContext());
        for(String playerName : info.split(", ")){
            //TODO: adjust to adapt to message format form server
            Game.getInstance().getGameModel().addNewPlayer(playerName);
        }
        Intent intent = new Intent(this, LobbyState.class);
        this.startActivity(intent);
    }

    public void joinedGameFailed(String error){
        //TODO: error melding
        System.out.println(error);
    }
    public void setNickname(View view){
        nickname = ((TextView)findViewById(R.id.nickname)).getText().toString();
        //TODO: slå sammen funksjon i newgamestate opp i en abstrakt klasse
        serverController.outputThread.sendNickname(nickname);
    }
}