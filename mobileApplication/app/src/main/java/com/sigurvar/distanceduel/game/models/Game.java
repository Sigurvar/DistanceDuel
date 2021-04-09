package com.sigurvar.distanceduel.game.models;

import com.sigurvar.distanceduel.game.controller.GameMode;
import com.sigurvar.distanceduel.game.controller.NormalMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {

    private static Game game = new Game( );
    private Game(){}
    public static Game getInstance(){
        return game;
    }

    protected GameMode gameMode;
    protected String gameCode;
    protected Stack<Question> questions;
    protected List<Player> players;
    protected boolean isHost;

    public void newGame(String gameCode, String self){
        players = new ArrayList<>();
        questions = new Stack<>();
        gameMode = new NormalMode(); //TODO skal være basert på settings til spillet
        this.gameCode=gameCode;
        this.players.add(new Player(self, true));
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public String getAllPlayersInGame(){
        StringBuilder str = new StringBuilder();
        for(Player p: players) str.append(p.getNickname()).append("\n");
        return str.toString();
    }
    public void setMeAsHost(){
        this.isHost=true;
    }
    public boolean isHost(){
        return isHost;
    }
    public void addNewPlayer(String nickname){
        this.players.add(new Player(nickname, false));
    }

}
