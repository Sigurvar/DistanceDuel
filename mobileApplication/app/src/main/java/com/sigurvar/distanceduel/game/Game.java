package com.sigurvar.distanceduel.game;

import android.content.Context;

import com.sigurvar.distanceduel.game.controller.GameController;
import com.sigurvar.distanceduel.game.controller.NormalController;
import com.sigurvar.distanceduel.game.controller.ChallengeController;
import com.sigurvar.distanceduel.game.models.GameModel;

public class Game {


    /** Game Modes **/
    public static final int NORMAL_MODE = 1;
    public static final int WRITE_QUESTION_MODE = 2;


    private static Game game = new Game();
    public static Game getInstance(){
        return game;
    }

    private Game(){ }

    private Context applicationContext;
    private GameModel gameModel;
    private GameController gameController;

    public GameModel setupNewGame(String myNickname, String gameCode, int gameMode, Context context){
        applicationContext = context;
        gameModel = new GameModel(myNickname, gameCode);
        if (gameMode==NORMAL_MODE) {
            gameController = new NormalController(context);
        }else if (gameMode==WRITE_QUESTION_MODE){
            gameController = new ChallengeController(context);
        }
        return gameModel;
    }

    public GameController getGameController() {
        return gameController;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    public Context getApplicationContext(){
        return applicationContext;
    }
}
