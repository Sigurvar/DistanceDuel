package com.sigurvar.distanceduel.game.models;

import com.sigurvar.distanceduel.utility.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Question {

    private String q; // TODO add location class
    private Unit unit;
    private HashMap<String, ArrayList<Double>> result;
    private Double correctAnswer;


    public Question(String q){
        result = new HashMap<>();
        this.q = q;
    }
    public String getQ(){
        return q;
    }

    public void setResult(JSONObject questionResult) {
        try{
            Iterator<String> keys = questionResult.keys();
            while(keys.hasNext()) {
                String playerName = keys.next();
                JSONArray name = questionResult.getJSONArray(playerName);
                ArrayList<Double> playerResult = new ArrayList<>();
                playerResult.add(name.getJSONObject(0).getDouble("Answer"));
                playerResult.add(name.getJSONObject(0).getDouble("Score"));
                result.put(playerName, playerResult);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ArrayList<Double>> getResult() {
        return result;
    }

    public void setCorrectAnswer( double correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Double getCorrectAnswer() {
        return correctAnswer;
    }
}
