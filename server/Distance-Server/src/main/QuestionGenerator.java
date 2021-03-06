package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import game.Question;
import game.Unit;

public final class QuestionGenerator {
		
	public  Stack<Question> generate(Unit unit, int numberOfQuestions) throws FileNotFoundException, JSONException {
		
		Random rand = new Random();
		Stack<Question> questions = new Stack<Question>();
		Scanner file = new Scanner(new File("src/main/worldcities.txt"));  
		file.useDelimiter("\n");
		
		ArrayList<String> cities = new ArrayList<String>();

		while (file.hasNext()){	
			cities.add(new String(file.next()));
		}
		file.close();

		for (int i = 0; i < numberOfQuestions; i ++) {
			
			int a = rand.nextInt(cities.size());
			int b = rand.nextInt(cities.size());
			while (a==b) {
				b = rand.nextInt(cities.size());
			}
			
			String placeA = cities.get(a);
			String placeB = cities.get(b);
			System.out.println(placeA + placeB);
			
			questions.push(create(placeA, placeB, unit));
		}
		return questions;
	}
	
	public Question create(String placeA, String placeB, Unit unit) throws JSONException {
		JSONObject jsonA = APIController.getCoordinate(placeA);
		JSONObject jsonB = APIController.getCoordinate(placeB);
		
		JSONArray dataA = jsonA.getJSONArray("data");
		
		double placeAx = dataA.getJSONObject(0).getDouble("longitude");
		double placeAy = dataA.getJSONObject(0).getDouble("latitude");
		
		JSONArray dataB = jsonB.getJSONArray("data");
		
		double placeBx = dataB.getJSONObject(0).getDouble("longitude");
		double placeBy = dataB.getJSONObject(0).getDouble("latitude");
		
		float distance = (float) calculateDistance(placeAx, placeAy, placeBx, placeBy, unit);
		return new Question(placeA, placeB, distance, unit);
		
	}
	
	public double calculateDistance(double placeAx, double placeAy, double placeBx, double placeBy, Unit unit) {
		
		double theta = placeAx-placeBx;
		double dist = Math.sin(deg2rad(placeAy)) * Math.sin(deg2rad(placeBy)) + Math.cos(deg2rad(placeAy)) * Math.cos(deg2rad(placeBy)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		dist = (dist / unit.getLength())*1000;
		System.out.println("Number of "+unit.name()+"'s: "+dist);
		return (dist);
	}
	private double deg2rad(double deg) {
		  return (deg * Math.PI / 180.0);
		}

	private double rad2deg(double rad) {
		  return (rad * 180.0 / Math.PI);
		}

}
