package com.sigurvar.distanceduel.states;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.models.Game;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;


public class LobbyState extends State {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby_state);
        StateController.getInstance().setState(this);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.gameCode)).setText(intent.getStringExtra("gameCode"));
        Game game = Game.getInstance();
        boolean isHost = false;
        String player = null;
        try{
            String nickname = intent.getStringExtra("players");
            ((TextView)findViewById(R.id.players)).setText(nickname);
            player = nickname;
        }catch (Exception e){
            isHost = true;
            findViewById(R.id.startGame).setVisibility(View.VISIBLE);
        }
        game.newGame(intent.getStringExtra("gameCode"), intent.getStringExtra("nickname"), player, isHost);
    }

    public void newPlayerJoinedGame(String playerName){
        Game.getInstance().addNewPlayer(playerName);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = findViewById(R.id.players);
                tv.setText(Game.getInstance().getAllPlayersInGame());
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