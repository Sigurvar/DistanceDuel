package com.sigurvar.distanceduel.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class NetworkComponent {

    public int status;
    public NetworkComponent(){
        status = -1;
    }
    public void SendRequest(String url, String data) throws IOException {
        URL url1 = new URL(url);
        HttpURLConnection con = (HttpURLConnection) url1.openConnection();
        con.setRequestMethod("GET");
        this.status = con.getResponseCode();
        System.out.print(con.getResponseCode());
        System.out.println(con.getResponseMessage());
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        System.out.println(content);

    }
}
