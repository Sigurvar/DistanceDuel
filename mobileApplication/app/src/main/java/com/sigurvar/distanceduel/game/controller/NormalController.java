package com.sigurvar.distanceduel.game.controller;

import android.content.Context;

public class NormalController extends GameController{

    public NormalController(String gameCode, String nickname, Context context){
        super(gameCode, nickname, context);
        super.setGameController(this);
    }
}
