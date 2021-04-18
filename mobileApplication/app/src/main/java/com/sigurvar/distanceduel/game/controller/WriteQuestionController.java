package com.sigurvar.distanceduel.game.controller;

import android.content.Context;
import android.content.Intent;

import com.sigurvar.distanceduel.game.views.WaitResultState;
import com.sigurvar.distanceduel.game.views.WriteQuestionState;
import com.sigurvar.distanceduel.utility.ServerController;

public class WriteQuestionController extends GameController{

    public WriteQuestionController(Context context){
        super(context);
    }

    @Override
    public void startGame() {

    }

    public void createQuestion(){
        Intent intent = new Intent(context, WriteQuestionState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
