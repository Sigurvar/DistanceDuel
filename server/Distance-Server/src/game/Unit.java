package game;

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
	
}
