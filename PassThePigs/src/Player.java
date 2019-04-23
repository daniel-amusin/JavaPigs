
public class Player {
	private String name;
	private Pig pig1;
	private Pig pig2;
	private int points;
	
	/*
	 * Initialize player with name
	 */
	public Player (String name) {
		this.name = name;
		this.pig1 = new Pig();
		this.pig2 = new Pig();
	}
	
	/*
	 * Initialize player with name and pigs
	 */
	public Player (String name, Pig pig1, Pig pig2) {
		this.name = name;
		this.pig1 = pig1;
		this.pig2 = pig2;
	}
	
	/*
	 * Set player's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * @return player's name
	 */
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
	 * Get value of rolls
	 * 0 - no dot
	 * 1 - dot
	 * 2 - razorback
	 * 3 - trotter
	 * 4 - snouter
	 * 5 - leaning jowler
	 * 
	 * @return point value of the roll
	 */
	private static int pointValue (int pig1roll, int pig2roll) {
		// pig out
		if (pig1roll == 0 && pig2roll == 1 || pig1roll == 1 && pig2roll == 0) {
			return 0;
		// 1 point
		} else if (pig1roll == 1 && pig2roll == 0 || pig1roll == 0 && pig2roll == 1) {
			return 1;
		// double razorback
		} else if (pig1roll == 2 && pig2roll == 2) {
			return 20;
		// double trotter
		} else if (pig1roll == 3 && pig2roll == 3) {
			return 20;
		// double snouter
		} else if (pig1roll == 4 && pig2roll == 4) {
			return 40;
		// double leaning jowler
		} else if (pig1roll == 5 && pig2roll == 5) {
			return 60;
		// individual pig rolls added
		} else {
			int score = 0;
			switch (pig1roll) {
				case 2:
					score += 5;
					break;
				case 3:
					score += 5;
					break;
				case 4:
					score += 10;
					break;
				case 5: 
					score += 15;
					break;
			}
			switch (pig2roll) {
			case 2:
				score += 5;
				break;
			case 3:
				score += 5;
				break;
			case 4:
				score += 10;
				break;
			case 5: 
				score += 15;
				break;
		}
			return score;
		}
	}
	
	/*
	 * Add points to player score
	 */
	public void addPoints(int points) {
		this.points += points;
	}
	
	/*
	 * @return player's points
	 */
	public int getPoints() {
		return this.points;
	}
}
