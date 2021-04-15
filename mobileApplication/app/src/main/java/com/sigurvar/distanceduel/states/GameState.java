package com.sigurvar.distanceduel.states;

import android.os.Bundle;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.controller.GameMode;
import com.sigurvar.distanceduel.game.models.Player;
import com.sigurvar.distanceduel.game.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class GameState extends State {
/*
    protected GameMode gameMode;
    protected Stack<Question> questions;
    protected List<Player> players;
    protected boolean isHost;


    public GameState(){
        players = new ArrayList<>();
    }

    public GameMode getGameMode() {
        return gameMode;
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
    }
}