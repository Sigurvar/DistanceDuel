package com.sigurvar.distanceduel.utility;

import com.sigurvar.distanceduel.game.views.WriteQuestionState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

public class APIController {

    private String SendRequest(String page) throws IOException {
        URL url = new URL(page);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();

    }

    public void run(String text){
        new Thread(new API(text)).start();

    }
    private class API implements Runnable {
        private final String suggest;
        protected API(String suggest){
            this.suggest = suggest;

        }
        @Override
        public void run() {
            try {
                String page = "https://geocode.arcgis.com/arcgis/rest/services/World/GeocodeServer/suggest?text=" + suggest + "&outFields=*&maxSuggestions=3&f=json";
                JSONObject response = new JSONObject(SendRequest(page));
                ArrayList<String> results = new ArrayList<>();
                for (int i=0;i<response.getJSONArray("suggestions").length();i++ ){
                    results.add(response.getJSONArray("suggestions").getJSONObject(i).getString("text"));
                }
                ((WriteQuestionState)StateController.getInstance().getState()).displayInfo(results);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
