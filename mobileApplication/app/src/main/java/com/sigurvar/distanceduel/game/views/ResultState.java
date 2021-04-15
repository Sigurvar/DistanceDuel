package com.sigurvar.distanceduel.game.views;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.StateController;

public class ResultState extends ReceiveQuestionState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_state);
        StateController.getInstance().setState(this);
        displayResult();
        canNextQuestion();
    }

    public void canNextQuestion(){
        findViewById(R.id.nextQuestion).setVisibility(View.VISIBLE);
    }

    public void nextQuestion(View view){

    }

    public void displayResult(){
        ((TextView)findViewById(R.id.result)).setText("Result:\n"+gameModel.getQuestionResult());
    }
}