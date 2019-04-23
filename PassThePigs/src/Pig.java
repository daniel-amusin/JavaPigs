import java.util.Random;
public class Pig {
	public Pig() {
		
	}
	
	/*
	 * Roll pig
	 * 0 - no dot
	 * 1 - dot
	 * 2 - razorback
	 * 3 - trotter
	 * 4 - snouter
	 * 5 - leaning jowler
	 */
	public int roll() {
		Random generator = new Random();
		
		double roll = generator.nextDouble();
		
		if (roll < .35)
			return 0;
		else if (roll < .65)
			return 1;
		else if (roll < .85)
			return 2;
		else if (roll < .95)
			return 3;
		else if (roll < .99)
			return 4;
		else
			return 5;
	}
}
