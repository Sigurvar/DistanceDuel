package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ResultState extends ReceiveQuestionState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_state);
        StateController.getInstance().setState(this);
        displayResult();
        setHostButton();
    }

    public void setHostButton(){
        if(gameModel.isHost()) {
            if (gameModel.isCreateMoreQuestions()) {
                findViewById(R.id.moreQuestions).setVisibility(View.VISIBLE);
            } else if (!gameModel.isNoMoreQuestions()) {
                findViewById(R.id.nextQuestion).setVisibility(View.VISIBLE);
            }
        }
        if (!gameModel.isCreateMoreQuestions() && gameModel.isNoMoreQuestions()){
            //TODO: Legg til tekst om at spillet er over
            findViewById(R.id.finalResult).setVisibility(View.VISIBLE);
        }

    }
    public void nextQuestion(View view){
        ServerController.getInstance().outputThread.getNextQuestion();
    }
    public void finalResult(View view){
        Intent intent = new Intent(this, FinalResultState.class);
        this.startActivity(intent);
    }
    public void moreQuestions(View view){

    }

    public void displayResult(){
        String nickNames = "";
        String answers = "";
        String scores = "";
        String s = "";
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(gameModel.getFinalResult().trim());
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()) {
                String key = keys.next();
                nickNames += key + "\n";
                JSONArray name = jsonObject.getJSONArray(key);
                double answer = name.getJSONObject(0).getDouble("Answer");
                double score = name.getJSONObject(0).getDouble("Score");
                answers += String.valueOf(answer) + "\n";
                scores += String.valueOf((int) score) + "\n";
                s += String.format("%-12s", key);
                s += String.format("%-12s", answer);
                s += String.format("%s", (int)score);
                s += "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ((TextView)findViewById(R.id.result)).setText(s);
       /* ((TextView)findViewById(R.id.names)).setText(nickNames);
        ((TextView)findViewById(R.id.answers)).setText(answers);
        ((TextView)findViewById(R.id.scores)).setText(scores);*/
    }
}