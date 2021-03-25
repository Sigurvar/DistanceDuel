package com.sigurvar.distanceduel.game.models;

import com.sigurvar.distanceduel.utility.Unit;

import java.util.HashMap;

public class Question {

    private String q; // TODO add location class
    private Unit unit;
    private HashMap<Player, Float> answers; // TODO: create an answer class
    private Float answer;
}
