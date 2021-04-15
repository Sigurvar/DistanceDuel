package com.sigurvar.distanceduel.game.controller;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.game.models.GameModel;
import com.sigurvar.distanceduel.game.views.AnswerState;
import com.sigurvar.distanceduel.game.views.FinalResultState;
import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.game.views.ReceiveQuestionState;
import com.sigurvar.distanceduel.game.views.ResultState;
import com.sigurvar.distanceduel.game.views.WaitResultState;
import com.sigurvar.distanceduel.game.views.WaitState;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.states.State;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;


public abstract class GameController {
    private GameState activeState;
    private Context context;
    private GameModel gameModel;

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

    public abstract void startGame();

    public void receivedQuestion(String message) {
        gameModel.newQuestion(message);
        Intent intent = new Intent(context, AnswerState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        //TODO implementer timer her
    }

    public void sendAnswer(String answer){
        Intent intent = new Intent(context, WaitResultState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ServerController.getInstance().outputThread.sendAnswer(answer);
    }
    public void receivedResult(String result){
        gameModel.gotResultForQuestion(result);
        Intent intent = new Intent(context, ResultState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void receivedFinalResult(String result){
        Intent intent = new Intent(context, FinalResultState.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        gameModel.gotResultForQuestion(result);
    }
}
