package com.sigurvar.distanceduel.game.models;

import android.content.Context;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.game.Game;
import com.sigurvar.distanceduel.utility.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class Question {

    private String locationA;
    private String locationB;
    private Unit unit;
    private HashMap<String, ArrayList<Double>> result;
    private Double correctAnswer;
    private final Context context = Game.getInstance().getApplicationContext();


    public Question(String jsonString){
        result = new HashMap<>();
        try{
            JSONObject question = new JSONObject(jsonString);
            locationA = question.getString("locationA");
            locationB = question.getString("locationB");
            unit = Unit.valueOf(question.getString("unit"));
            System.out.println(unit);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    public String getQ(){
        System.out.println(unit);
        System.out.println(context);
        String question = context.getString(R.string.how_many)+" "+context.getString(unit.getResourceId())
                +" "+context.getString(R.string.are_between)+" "+ locationA
                +" "+context.getString(R.string.and)+" "+locationB+"?";
        return question;
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
