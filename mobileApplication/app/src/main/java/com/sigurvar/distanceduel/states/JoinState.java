package com.sigurvar.distanceduel.states;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
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

    public void joinedGameSuccessful(String players){
        Intent intent = new Intent(this, LobbyState.class);
        intent.putExtra("gameCode", gameCode);
        intent.putExtra("players", players);
        intent.putExtra("nickname", nickname);
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