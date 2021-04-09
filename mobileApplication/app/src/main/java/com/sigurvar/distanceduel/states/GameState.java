package com.sigurvar.distanceduel.states;

import android.os.Bundle;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.controller.GameMode;
import com.sigurvar.distanceduel.game.models.Player;
import com.sigurvar.distanceduel.game.models.Question;
import com.sigurvar.distanceduel.utility.ServerController;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class GameState extends State {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
    }

    @Override
    public void onBackPressed() {
        // TODO: Legg til spm om man er sikker på å forlate spillet, hvis ja kjør leaveGame
        super.onBackPressed();
    }

    private void leaveGame(){
        //TODO: send til server at spiller forlater spillet og gå til startskjerm (main)
    }
}