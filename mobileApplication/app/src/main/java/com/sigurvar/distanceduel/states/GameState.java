package com.sigurvar.distanceduel.states;

import android.os.Bundle;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.controller.GameController;
import com.sigurvar.distanceduel.game.models.GameModel;

public abstract class GameState extends State {

    protected GameController gameController = Game.getInstance().getGameController();
    protected GameModel gameModel = Game.getInstance().getGameModel();

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

    public void sendToController(){

    }
}