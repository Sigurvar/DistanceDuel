package com.sigurvar.distanceduel.game.views;

import com.sigurvar.distanceduel.states.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class ResultState extends GameState {


    protected static Map<String, Double> sortByComparator(Map<String, Double> unsortMap) {
        List<Map.Entry<String, Double>> list = new LinkedList<Map.Entry<String, Double>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    protected static Map<String, ArrayList<Double>> sort(Map<String, ArrayList<Double>> unsortMap) {
        List<Map.Entry<String, ArrayList<Double>>> list = new LinkedList<Map.Entry<String, ArrayList<Double>>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<Double>>>() {
            public int compare(Map.Entry<String, ArrayList<Double>> o1, Map.Entry<String, ArrayList<Double>>o2) {
                return o2.getValue().get(1).compareTo(o1.getValue().get(1));
            }
        });
        Map<String, ArrayList<Double>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Double>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    public abstract void displayResult();
    public abstract HashMap getResult();

}
