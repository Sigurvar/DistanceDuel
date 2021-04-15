package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APIController {
	
	public APIController () {
    }
	public static String SendRequest(String url, String data) throws IOException {
        URL url1 = new URL(url);
        HttpURLConnection con = (HttpURLConnection) url1.openConnection();
        con.setRequestMethod("GET");
        System.out.print(con.getResponseCode());
        System.out.println(con.getResponseMessage());
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        String str = content.toString();
        return str;

    }
	
	public static JSONObject getCoordinate(String place) {
		
		String urlEncodedPlace = URLEncoder.encode(place, StandardCharsets.UTF_8);
		JSONObject jsonObj = null;
		System.out.println(urlEncodedPlace);
		String s = "[]";
		try {
			while (s.equals("[]")){
				String dataStr = SendRequest("http://api.positionstack.com/v1/forward?access_key=bbf5216ec5ff418aea03e7544e54729d&query="+urlEncodedPlace,"");
				jsonObj = new JSONObject(dataStr);
				JSONArray data = jsonObj.getJSONArray("data");
				s = data.get(0).toString();
			}
			
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return jsonObj;
		

	}

}

