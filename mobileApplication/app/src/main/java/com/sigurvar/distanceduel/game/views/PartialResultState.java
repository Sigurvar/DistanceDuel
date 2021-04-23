package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.utility.ServerController;
import com.sigurvar.distanceduel.utility.StateController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PartialResultState extends ResultState {

    private final DecimalFormat df = new DecimalFormat("###,###,###,###");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_state);
        StateController.getInstance().setState(this);
        df.setMaximumFractionDigits(12);
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
        String correctResultText = getString(R.string.correct_answer)+": "+ df.format(gameModel.getQuestionAnswer());
        ((TextView)findViewById(R.id.correctResult)).setText(correctResultText);
        TableLayout tableLayout = findViewById(R.id.result_table);
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View tableRow;
        HashMap<String, ArrayList<Double>> result = getResult();
        Iterator it = ((HashMap)result.clone()).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            String name = (String) pair.getKey();
            double answer = ((ArrayList<Double>)pair.getValue()).get(0);
            double score = ((ArrayList<Double>)pair.getValue()).get(1);
            tableRow = layoutInflater.inflate(R.layout.scoreboard_table_box, null);
            ((TextView)tableRow.findViewById(R.id.name)).setText(name);
            ((TextView)tableRow.findViewById(R.id.score)).setText(df.format(score));
            ((TextView)tableRow.findViewById(R.id.answer)).setText(df.format( answer));
            tableLayout.addView(tableRow);
            it.remove();
        }
    }

    @Override
    public HashMap<String, ArrayList<Double>> getResult() {
        return  gameModel.getQuestionResult();
    }
}