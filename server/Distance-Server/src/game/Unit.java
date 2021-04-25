package game;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Unit {
	BANANA((float)0.15),
	KILOMETER(1000),
	METER(1),
	CAR((float)4.5);
	
	private float length;
	
	Unit(float length) {
		this.length = length;
	}
	
	public float getLength() {
		return length;
	}
	private static final List<Unit> VALUES =
    Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Unit randomUnit()  {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
	
}
