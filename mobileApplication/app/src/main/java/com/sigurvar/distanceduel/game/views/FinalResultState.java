package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.MainState;
import com.sigurvar.distanceduel.utility.StateController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FinalResultState extends ResultState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result_state);
        StateController.getInstance().setState(this);
        displayResult();
    }

    public void goHome(View view){
        Intent intent = new Intent(this, MainState.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public HashMap<String, Double> getResult(){
        HashMap<String, Double> totalScores = new HashMap<>();
        HashMap<String, ArrayList<Double>> result = gameModel.getAndRemoveQuestionResult();
        while (result != null) {
            Iterator it = result.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                String name = (String) pair.getKey();
                double score = ((ArrayList<Double>) pair.getValue()).get(1);
                if (totalScores.containsKey(name)) {
                    totalScores.replace(name, totalScores.get(name) + score);
                } else {
                    totalScores.put(name, score);
                }
                it.remove();
            }
            result = gameModel.getAndRemoveQuestionResult();
            System.out.println(totalScores);
        }
        return totalScores;
    }
    public void displayResult() {
        HashMap<String, Double> totalScores = getResult();
        totalScores = (HashMap<String, Double>) sortByComparator(totalScores);

        TableLayout tableLayout = findViewById(R.id.result_table);
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View tableRow;

        Iterator it = totalScores.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            String name = (String) pair.getKey();
            double score = (double) pair.getValue();
            tableRow = layoutInflater.inflate(R.layout.final_scoreboard_table_box, null);
            ((TextView) tableRow.findViewById(R.id.name)).setText(name);
            ((TextView) tableRow.findViewById(R.id.totalScore)).setText(String.valueOf(score));
            tableLayout.addView(tableRow);
            it.remove();
        }
    }
}
