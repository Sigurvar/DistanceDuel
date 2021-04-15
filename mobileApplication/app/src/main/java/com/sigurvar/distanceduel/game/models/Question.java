package com.sigurvar.distanceduel.game.models;

import com.sigurvar.distanceduel.utility.Unit;

import java.util.HashMap;

public class Question {

    private String q; // TODO add location class
    private Unit unit;
    private HashMap<Player, Float> answers; // TODO: create an answer class
    private Float answer;
    private String result;


    public Question(String q){
        this.q = q;
    }
    public String getQ(){
        return q;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
