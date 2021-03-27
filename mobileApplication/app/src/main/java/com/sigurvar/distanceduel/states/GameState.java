package com.sigurvar.distanceduel.states;

import android.os.Bundle;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.controller.GameMode;
import com.sigurvar.distanceduel.game.models.Player;
import com.sigurvar.distanceduel.game.models.Question;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
    }
}