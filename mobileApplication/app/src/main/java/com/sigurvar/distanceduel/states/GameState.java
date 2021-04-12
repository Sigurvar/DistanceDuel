package com.sigurvar.distanceduel.states;

import android.os.Bundle;

import com.sigurvar.distanceduel.R;

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