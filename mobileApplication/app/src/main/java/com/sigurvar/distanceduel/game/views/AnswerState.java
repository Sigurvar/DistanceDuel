package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.utility.StateController;

public class AnswerState extends GameState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_state);
        StateController.getInstance().setState(this);
        displayQuestion();
        //Intent intent = getIntent();
        //((TextView)findViewById(R.id.question)).setText("Question:\n"+intent.getStringExtra("question"));

    }
    public void sendAnswer(View view){
        gameController.sendAnswer( ((TextView)findViewById(R.id.answer)).getText().toString());
    }

    public void displayQuestion(){
        ((TextView)findViewById(R.id.question)).setText("Question:\n"+gameModel.getQuestionText());
    }
}
