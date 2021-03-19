package game;

public enum Unit {
	BANANA(3),
	CAR(8);
	
	private float length;
	
	Unit(float length) {
		this.length = length;
	}
	
	public float getLength() {
		return length;
	}
	

}
