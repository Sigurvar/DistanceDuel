package com.sigurvar.distanceduel.game.views;

import android.content.Intent;

import com.sigurvar.distanceduel.states.GameState;

public class ReceiveQuestionState extends GameState {

    public void receivedQuestion(String question){
        Intent intent = new Intent(this, AnswerState.class);
        intent.putExtra("question", question);
        this.startActivity(intent);
    }
}
