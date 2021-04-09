package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.models.Game;
import com.sigurvar.distanceduel.utility.StateController;

public class ResultState extends ReceiveQuestionState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_state);
        StateController.getInstance().setState(this);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.result)).setText("The result is\n"+intent.getStringExtra("result"));
        if(Game.getInstance().isHost()){
            findViewById(R.id.nextQuestion).setVisibility(View.VISIBLE);
        }
    }

    public void nextQuestion(View view){

    }
}