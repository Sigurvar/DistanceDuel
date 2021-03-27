package com.sigurvar.distanceduel.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIController {
    public void get() {
        try {
            System.out.println(SendRequest("http://ip.jsontest.com/"));
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public String SendRequest(String page) throws IOException {
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
}
