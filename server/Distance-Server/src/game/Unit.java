package game;

public enum Unit {
	BANANA(15),
	CAR(300);
	
	private float length;
	
	Unit(float length) {
		this.length = length;
	}
	
	public float getLength() {
		return length;
	}
	

}
