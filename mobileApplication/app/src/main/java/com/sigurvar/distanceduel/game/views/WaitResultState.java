package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

public class WaitResultState extends GameState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_result_state);
        StateController.getInstance().setState(this);
        ServerController.getInstance().outputThread.sendAnswer(getIntent().getStringExtra("answer"));

    }

    public void receivedResult(String result){
        Intent intent = new Intent(this, ResultState.class);
        intent.putExtra("result", result);
        this.startActivity(intent);
    }

    public void receivedFinalResult(String result){
        Intent intent = new Intent(this, ResultState.class);
        intent.putExtra("result", result);
        intent.putExtra("done", "notnull");
        this.startActivity(intent);
    }
}
