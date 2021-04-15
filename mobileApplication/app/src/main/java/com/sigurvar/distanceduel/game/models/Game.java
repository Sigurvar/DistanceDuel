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

    public void newGame(String gameCode, String self, String player, boolean isHost){
        players = new ArrayList<>();
        questions = new Stack<>();
        gameMode = new NormalMode(); //TODO skal være basert på settings til spillet
        this.players.add(new Player(self, true));
        this.gameCode=gameCode;
        if (player!=null) this.players.add(new Player(player, false));
        this.isHost = isHost;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public String getAllPlayersInGame(){
        StringBuilder str = new StringBuilder();
        for(Player p: players) str.append(p.getNickname()).append("\n");
        return str.toString();
    }
    public void addNewPlayer(String nickname){
        this.players.add(new Player(nickname, false));
    }

}
