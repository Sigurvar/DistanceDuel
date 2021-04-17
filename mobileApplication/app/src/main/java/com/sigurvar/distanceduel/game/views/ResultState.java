package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

public class ResultState extends ReceiveQuestionState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_state);
        StateController.getInstance().setState(this);
        displayResult();
        if(gameModel.isHost())setHostButton();
    }

    public void setHostButton(){
        if(gameModel.isHost()) {
            if (gameModel.isCreateMoreQuestions()) {
                findViewById(R.id.moreQuestions).setVisibility(View.VISIBLE);
            } else if (gameModel.isNoMoreQuestions()) {
                findViewById(R.id.finalResult).setVisibility(View.VISIBLE);
            }else{
                findViewById(R.id.nextQuestion).setVisibility(View.VISIBLE);
            }
        }
    }
    public void seeFinalResultButton(){
        findViewById(R.id.finalResult).setVisibility(View.VISIBLE);
        findViewById(R.id.nextQuestion).setVisibility(View.INVISIBLE);
    }
    public void nextQuestion(View view){
        ServerController.getInstance().outputThread.getNextQuestion();
    }
    public void finalResult(View view){
        Intent intent = new Intent(this, FinalResultState.class);
        this.startActivity(intent);
    }
    public void moreQuestions(View view){

    }

    public void displayResult(){
        ((TextView)findViewById(R.id.result)).setText("Result:\n"+gameModel.getQuestionResult());
    }
}