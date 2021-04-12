package com.sigurvar.distanceduel.game.controller;

import android.content.Context;
import android.content.Intent;

import com.sigurvar.distanceduel.game.models.GameMode;
import com.sigurvar.distanceduel.game.models.GameModel;
import com.sigurvar.distanceduel.game.models.Player;
import com.sigurvar.distanceduel.game.models.Question;
import com.sigurvar.distanceduel.game.views.LobbyState;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.states.State;
import com.sigurvar.distanceduel.utility.StateController;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class GameController {
    private GameState activeState;
    private Context context;
    private static GameController gameController = null;
    public static GameController getCurrentGame(){
        return gameController;
    }
    protected void setGameController(GameController gameController){
        GameController.gameController = gameController;
    }
    protected GameController(String gameCode, String self, Context context){
        players = new ArrayList<>();
        questions = new Stack<>();
        gameModel = new GameModel(gameCode, false, GameMode.NORMAL);
        this.players.add(new Player(self, true));
    }


    /*private static GameController gameController = new GameController( );
    private GameController(){}
    public static GameController getInstance(){
        return gameController;
    }*/

    protected GameModel gameModel;
    protected Stack<Question> questions;
    protected List<Player> players;

    public void newGame(String gameCode, String self){
        players = new ArrayList<>();
        questions = new Stack<>();
        gameModel = new GameModel(gameCode, false, GameMode.NORMAL);
        this.players.add(new Player(self, true));
    }

    public String getAllPlayersInGame(){
        StringBuilder str = new StringBuilder();
        for(Player p: players) str.append(p.getNickname()).append("\n");
        return str.toString();
    }
    public void setMeAsHost(){
        gameModel.setAsHost();
        if (getCurrentState() instanceof LobbyState){
            ((LobbyState) getCurrentState()).canStartGame();
        }
    }
    public void addNewPlayer(String nickname){
        this.players.add(new Player(nickname, false));
    }
    public void goToLobby(){
        Intent intent = new Intent(context, LobbyState.class);
        context.startActivity(intent);
        LobbyState ls = ((LobbyState)getCurrentState());
        if(gameModel.isHost()){
            ls.canStartGame();
        }
        ls.setPlayersInGame(getAllPlayersInGame());

    }
    private State getCurrentState(){
        return StateController.getInstance().getState();
    }
    public void newPlayerJoinedGame(String playerName){
        addNewPlayer(playerName);
        ((LobbyState)getCurrentState()).setPlayersInGame(getAllPlayersInGame());
    }
}
