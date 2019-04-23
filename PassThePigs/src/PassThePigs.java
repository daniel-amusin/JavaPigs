import java.util.ArrayList;
import java.util.Scanner;

public class PassThePigs {
	private static ArrayList<Player> players;
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		boolean stopPlaying = false;
		while (!stopPlaying) {
			initiateGame();
			playGame();
			stopPlaying = true;
			//TODO: Add play again menu
		}
	}
	
	private static void initiateGame() {
		players = new ArrayList<Player>();
		int numPlayers = -1;
		
		System.out.println("How many players do you have?");
		boolean validInput = false;
		while (!validInput)
		try {
			numPlayers = Integer.parseInt(in.nextLine());
			if (numPlayers < 1) {
				throw new Exception();
			}
			validInput = true;
		} catch (Exception e) {
			System.out.println("That is not a valid number. Please try again.");
		}
		
		for (int i = 0; i < numPlayers; i++) {
			String name = "";
			validInput = false;
			System.out.print("What is the name of player " + (i + 1) + ": ");
			try {
				name = in.nextLine();
				validInput = true;
			} catch (Exception e) {
				System.out.println("That is not a valid name. Please try again.");
			}
			players.add(new Player(name));
		}
	}

	private static void playGame() {
		boolean gameOver = false;
		int currPlayer = 0;
		boolean lastRound = false;
		int lastPlayer = 0;
		int mostPoints = 0;
		Player winning = players.get(0);
		while (!gameOver) {
			if (lastRound && currPlayer == lastPlayer) {
				System.out.println(winning.getName() + " has won!");
				return;
			}
			
			turn(currPlayer, players.get(currPlayer));
			
			if (players.get(currPlayer).getPoints() > 100) {
				lastRound = true;
				lastPlayer = currPlayer;
			}
			
			if (players.get(currPlayer).getPoints() > mostPoints) {
				mostPoints = players.get(currPlayer).getPoints();
				winning = players.get(currPlayer);
			}
			currPlayer++;
			
			if (currPlayer == (players.size())) {
				currPlayer = 0;
			}
		}
	}
	
	private static void turn(int currPlayer, Player player) {
		boolean stopRolling = false;
		int turnPoints = 0;
		System.out.println("It is " + player.getName() + "'s turn! What would you like to do?");
		System.out.println("Points: " + player.getPoints());
		while (!stopRolling) {
			System.out.print("0 to roll, 1 to pass: ");
			int input = 0;
			try {
				boolean validInput = false;
				while (!validInput) {
					input = Integer.parseInt(in.nextLine());
					if (input != 0 && input != 1) {
						throw new Exception();
					} 
					validInput = true;
				}
			} catch (Exception e) {
				System.out.println("That is not a valid choice, please try again!");
			}
			
			if (input == 0) {
				int newRoll = players.get(currPlayer).roll();
				
				if (newRoll == 0) {
					System.out.println("You pigged out.");
					return;
				} else {
					turnPoints += newRoll;
					System.out.println("You got " + newRoll + " points. You now have " + turnPoints + " this turn.");
					System.out.println("Your total score would be: " + (player.getPoints() + turnPoints));
				}
			} else {
				player.addPoints(turnPoints);
				return;
			}
		}
	}
}
