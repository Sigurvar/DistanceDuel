package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import game.Question;
import game.Unit;

public final class QuestionGenerator {
		
	public Stack<Question> generate(Unit unit, int numberOfQuestions) throws FileNotFoundException {
		Random rand = new Random();
		Stack<Question> product = new Stack<Question>();
		Scanner file = new Scanner(new File("server/Distance-Server/src/main/worldcities.txt"));  
		file.useDelimiter("\n");
		
		int numberoflines = 0;
		ArrayList<String> cities = new ArrayList<String>();

		while (file.hasNext()){	
			cities.add(new String(file.next()));
			numberoflines++;
		}
		System.out.println(cities);

		for (int i = 0; i < numberOfQuestions; i ++) {
			
			int a = rand.nextInt(numberoflines);
			int b = rand.nextInt(numberoflines);
			while (a==b) {
				b = rand.nextInt(numberoflines);
			}
			
			String placeA = cities.get(a);
			String placeB = cities.get(b);

			//float distance = calculateDistance(api.getCoordinate(placeA), api.getCoordinate(placeB));
			
			product.push(create(placeA, placeB, unit));
		}
		return null;
		
	}
	
	public Question create(String placeA, String placeB, Unit unit) {
		float distance = calculateDistance(APIController.getCoordinate(placeA), APIController.getCoordinate(placeB));
		return new Question(placeA, placeB, distance, unit);
		
	}
	
	public float calculateDistance(JSONObject a, JSONObject b) {
		return sqrt(pwr((a[0]-b[0]),2) + pwr((a[1]-b[1]),2));
	}
	/*public static void main(String[] args) throws FileNotFoundException{
		QuestionGenerator q = new QuestionGenerator();

		q.generate(Unit.BANANA, 4);

	}*/

}
