import java.util.Random;
public class Pig {
	private String name;
	
	public Pig() {
		name = "Oinker";
	}
	
	public Pig(String name) {
		this.name = name;
	}
	
	/*
	 * Roll pig and return result
	 * Thanks to http://passpigs.tripod.com/prob.html for probabilities.
	 * 0 - no dot
	 * 1 - dot
	 * 2 - razorback
	 * 3 - trotter
	 * 4 - snouter
	 * 5 - leaning jowler
	 * 
	 * @return integer result of roll
	 */
	public int roll() {
		Random generator = new Random();
		
		double roll = generator.nextDouble();
		
		if (roll < .35) {
			System.out.println("Rolled no dot");
			return 0;
		} else if (roll < .65) {
			System.out.println("Rolled dot");
			return 1;
		} else if (roll < .85) {
			System.out.println("Rolled razorback");
			return 2;
		} else if (roll < .95) {
			System.out.println("Rolled trotter");
			return 3;
		} else if (roll < .99) {
			System.out.println("Rolled snouter");
			return 4;
		} else
			System.out.println("Rolled leaning jowler");
			return 5;
	}
	
	/*
	 * @return pig's name
	 */
	public String getName() {
		return this.name;
	}
}
