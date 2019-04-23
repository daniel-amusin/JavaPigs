
public class Player {
	private String name;
	private Pig pig1;
	private Pig pig2;
	private int points;
	
	public Player (String name) {
		this.name = name;
		this.pig1 = new Pig();
		this.pig2 = new Pig();
	}
	
	public Player (String name, Pig pig1, Pig pig2) {
		this.name = name;
		this.pig1 = pig1;
		this.pig2 = pig2;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	/*
	 * @return point value of roll
	 */
	public int roll () {
		int pig1roll = pig1.roll();
		int pig2roll = pig2.roll();
		
		return pointValue(pig1roll, pig2roll);
	}
	
	/*
	 * @return point value of the roll
	 */
	private static int pointValue (int pig1roll, int pig2roll) {
		//TODO: Set up point values
		if (pig1roll == 0 && pig2roll == 1 || pig1roll == 1 && pig2roll == 0) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	public int getPoints() {
		return this.points;
	}
}
