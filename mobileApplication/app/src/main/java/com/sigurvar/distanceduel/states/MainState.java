package com.sigurvar.distanceduel.states;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.APIController;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

import org.w3c.dom.Text;

public class MainState extends State {

    ServerController serverController = ServerController.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_state);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        StateController.getInstance().setState(this);
    }

    public void createGame(View view){
        //Log.i("Game", "Starting new game");
        //serverController.outputThread.sendNewGame("Dette er innstilligene");
        Intent myIntent = new Intent(this, NewGameState.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
    }

    public void joinGame(View view){
        Intent myIntent = new Intent(this, JoinState.class);
        //myIntent.putExtra("key", value); //Optional parameters
        this.startActivity(myIntent);
        //TextView tv = findViewById(R.id.gameCode);
        //serverController.outputThread.sendGameCode(tv.getText().toString());
    }

    public void sendAnswer(View view){
        TextView tv = findViewById(R.id.answer);
        serverController.outputThread.sendAnswer(tv.getText().toString());
    }
    public void leaveGame(View view){serverController.disconnect();}
    public void connect(View view){
        serverController.connect(this);
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

    public void autocomplete(View view){
        TextView tv = findViewById(R.id.search_text);
        System.out.println(new APIController().suggestPlace(tv.getText().toString()));

    }
}