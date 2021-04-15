package com.sigurvar.distanceduel.game;

import android.content.Context;

import com.sigurvar.distanceduel.game.controller.GameController;
import com.sigurvar.distanceduel.game.controller.NormalController;
import com.sigurvar.distanceduel.game.models.GameModel;

public class Game {

    private static Game game = new Game();
    public static Game getInstance(){
        return game;
    }

    private Game(){ }

    private Context applicationContext;
    private GameModel gameModel;
    private GameController gameController;

    public void setupNewGame(String myNickname, String gameCode, Context context){ // må også sende inn setting her
        gameModel = new GameModel(myNickname, gameCode);
        gameController = new NormalController(context);// TODO legg til if på settings her
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }
}
