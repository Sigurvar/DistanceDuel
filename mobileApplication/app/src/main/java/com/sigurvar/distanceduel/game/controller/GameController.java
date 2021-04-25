package com.sigurvar.distanceduel.game.controller;

import android.content.Context;
import android.content.Intent;

import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.models.GameModel;
import com.sigurvar.distanceduel.game.views.AnswerState;
import com.sigurvar.distanceduel.game.views.PartialResultState;
import com.sigurvar.distanceduel.game.views.WaitResultState;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.utility.ServerController;


public abstract class GameController {
    protected Context context;
    protected GameModel gameModel;

    public GameController(Context context){
        this.context = context;
        this.gameModel = Game.getInstance().getGameModel();
    }
    public void newPlayerJoinedGame(String playerName){
        gameModel.addNewPlayer(playerName);
    }
    public void playerLeftTheGame(String playerName){
        gameModel.playerLeftTheGame(playerName);
    }
    public void youAreOwner() {gameModel.setAsHost();}
    public abstract void startGame();

    public void receivedQuestion(String message) {
        gameModel.newQuestion(message);
        Intent intent = new Intent(context, AnswerState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void sendAnswer(String answer){
        Intent intent = new Intent(context, WaitResultState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ServerController.getInstance().outputThread.sendAnswer(answer);
    }
    public void receivedResult(String result){
        gameModel.gotResultForQuestion(result);
        Intent intent = new Intent(context, PartialResultState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
