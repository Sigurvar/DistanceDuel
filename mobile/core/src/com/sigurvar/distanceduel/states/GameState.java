package com.sigurvar.distanceduel.states;

import com.sigurvar.distanceduel.controller.GameMode;
import com.sigurvar.distanceduel.models.Player;
import com.sigurvar.distanceduel.models.Question;

import java.util.List;
import java.util.Stack;

public abstract class GameState extends State {

    private GameMode gameMode;
    private Stack<Question> questions;
    private List<Player> players;

    public GameState(){

    }

    public GameMode getGameMode() {
        return gameMode;
    }
}
