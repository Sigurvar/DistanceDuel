package com.sigurvar.distanceduel.game.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.sigurvar.distanceduel.R;
import com.sigurvar.distanceduel.states.GameState;
import com.sigurvar.distanceduel.states.MainState;
import com.sigurvar.distanceduel.utility.StateController;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FinalResultState extends GameState {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result_state);
        StateController.getInstance().setState(this);
        displayResult();
    }

    public void goHome(View view){

        //TODO: go to main and delete onBackButtonPressedStack
        Intent intent = new Intent(this, MainState.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    /*private void sortByValue(HashMap<String, Float> score)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>((Collection<? extends Map.Entry<String, Integer>>) score.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
    }*/
    private static Map<String, Double> sortByComparator(Map<String, Double> unsortMap)
    {
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Double>>()
        {

            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2)
            {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    public void displayResult() {
        Map<String, Double> totalScores = new HashMap<>();
        TableLayout tableLayout = findViewById(R.id.result_table);
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View tableRow;
        System.out.println("Displayeing result");

        HashMap<String, ArrayList<Double>> result = gameModel.getAndRemoveQuestionResult();
        System.out.println(result);
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
        totalScores = sortByComparator((HashMap<String, Double>) totalScores);
        Iterator it = totalScores.entrySet().iterator();
        System.out.println(totalScores);
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
