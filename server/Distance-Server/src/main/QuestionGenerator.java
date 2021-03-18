package main;
import java.util.Stack;

import game.Question;
import game.Unit;

public final class QuestionGenerator {
		
	public static Stack<Question> generate(Unit unit, int numberOfQuestions) {
		
		Stack<Question> product = new Stack<Question>();
		File file = FileReader("path.csv");
		
		for (int i = 0; i < numberOfQuestions; i ++) {
			
			int a = randomint(0,file.numberoflines());
			int b = randomint(0,file.numberoflines());
			while (a==b) {
				b = randomint(0,file.numberoflines());
			}
			
			String placeA = file.readLine(a);
			String placeB = file.readLine(b);
			
			float distance = calculateDistance(api.getCoordinate(placeA), api.getCoordinate(placeB));
			
			product.push(new Question(placeA, placeB, distance, unit));
		}
		return null;
		
	}
	
	public Question create(String placeA, String placeB, Unit unit) {
		float distance = calculateDistance(APIController.getCoordinate(placeA), APIController.getCoordinate(placeB));
		return new Question(placeA, placeB, distance, unit);
		
	}
	
	public float calculateDistance(Json a, Json b) {
		return sqrt(pwr((a[0]-b[0]),2) + pwr((a[1]-b[1]),2));
	}

}
