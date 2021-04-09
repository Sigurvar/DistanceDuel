package com.sigurvar.distanceduel.states;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.models.Game;
import com.sigurvar.distanceduel.game.views.ReceiveQuestionState;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;


public class LobbyState extends ReceiveQuestionState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_state);
        StateController.getInstance().setState(this);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.gameCode)).setText(intent.getStringExtra("gameCode"));
        Game game = Game.getInstance();
        game.newGame(intent.getStringExtra("gameCode"), intent.getStringExtra("nickname"));

        String players = intent.getStringExtra("players");
        if (players==null){
            game.setMeAsHost();
            findViewById(R.id.startGame).setVisibility(View.VISIBLE);
        }else{
            for(String playerName:players.split(", ")){
                //TODO: adjust to adapt to message format form server
                game.addNewPlayer(playerName);
            }
            updatePlayersInGame();
        }
    }

    public void newPlayerJoinedGame(String playerName){
        Game.getInstance().addNewPlayer(playerName);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updatePlayersInGame();
            }
        });
    }
    private void updatePlayersInGame(){
        TextView tv = findViewById(R.id.players);
        tv.setText(Game.getInstance().getAllPlayersInGame());
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