package com.sigurvar.distanceduel.states;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;

public class MainState extends State {

    ServerController serverController = ServerController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_state);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

    }

    public void createGame(View view){
        Log.i("Game", "Starting new game");
        serverController.sendNewGame("Dette er innstilligene");
        serverController.start();
    }

    public void joinGame(View view){
        TextView tv = findViewById(R.id.gameCode);
        serverController.sendGameCode(tv.getText().toString());
        serverController.start();
    }
    public void setNickname(View view){
        TextView tv = findViewById(R.id.nickname);
        serverController.sendNickname(tv.getText().toString());

    }
    public void connect(View view){
        serverController.connect();
    }
}