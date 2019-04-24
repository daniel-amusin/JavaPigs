import java.util.ArrayList;
import java.util.Scanner;

public class PassThePigs {
	private static ArrayList<Player> players;
	private static Scanner in = new Scanner(System.in);
	private static int WINNING_SCORE = 100;

	/*
	 * Main class to run the game
	 */
	public static void main(String[] args) {
		boolean stopPlaying = false;
		while (!stopPlaying) {
			// start game and play
			initiateGame();
			playGame();
			
			System.out.println("Would you like to play again? (0 - yes, 1 - no)");
			int input = -1;
			boolean validInput = false;
			while (!validInput) {
				try {
					input = Integer.parseInt(in.nextLine());
					if (input != 0 && input != 1) {
						throw new Exception();
					}
					validInput = true;
				} catch (Exception e) {
					System.out.println("That is not a valid choice. Please try again?");
				}
			}
			if (input == 1) {
				stopPlaying = true;
			}
		}
	}
	
	/*
	 * Initiate an instance of the game
	 */
	private static void initiateGame() {
		players = new ArrayList<Player>();
		int numPlayers = -1;
		
		//get number of players
		System.out.print("How many players do you have? ");
		boolean validInput = false;
		while (!validInput)
		try {
			numPlayers = Integer.parseInt(in.nextLine());
			if (numPlayers < 1) {
				throw new NumberFormatException();
			}
			validInput = true;
		} catch (NumberFormatException e) {
			System.out.print("That is not a valid number. Please try again: ");
		}
		
		//get player names
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

	/*
	 * Runs an instance of the game
	 */
	private static void playGame() {
		boolean gameOver = false;
		//current player whose turn it is
		int currPlayer = 0;
		// check if final round
		boolean lastRound = false;
		// if final round which player to end at
		int lastPlayer = 0;
		// current highest score
		int mostPoints = 0;
		// player who is winning
		Player winningPlayer = players.get(0);
		while (!gameOver) {
			// if someone won
			if (lastRound && currPlayer == lastPlayer) {
				System.out.println(winningPlayer.getName() + " has won!");
				return;
			}
			
			// take player turn
			turn(players.get(currPlayer));
			
			// check if player has winning score to initiate last round
			if (players.get(currPlayer).getPoints() > WINNING_SCORE) {
				System.out.println(players.get(currPlayer).getName() + " has over " + WINNING_SCORE + " points. This is the final round.");
				lastRound = true;
				lastPlayer = currPlayer;
			}
			
			// check who has the highest points
			if (players.get(currPlayer).getPoints() > mostPoints) {
				mostPoints = players.get(currPlayer).getPoints();
				winningPlayer = players.get(currPlayer);
			}
			// go to next player
			currPlayer++;
			// if go through all players, start back at first
			if (currPlayer == (players.size())) {
				currPlayer = 0;
			}
		}
	}
	
	/*
	 * Single turn of a given player
	 */
	private static void turn(Player player) {
		boolean stopRolling = false;
		// points this turn
		int turnPoints = 0;
		System.out.println("It is " + player.getName() + "'s turn! (" + player.getPoints() + " points)");
		while (!stopRolling) {
			System.out.print("0 to roll, 1 to pass: ");
			// get choice of player
			int input = 0;
			boolean validInput = false;
			while (!validInput) {
				try {
					input = Integer.parseInt(in.nextLine());
					if (input != 0 && input != 1) {
						throw new NumberFormatException();
					} 
					validInput = true;					
				} catch (NumberFormatException e) {
					System.out.print("That is not a valid choice, please try again: ");
				}
			}
			
			// choice to roll
			if (input == 0) {
				int newRoll = player.roll();
				
				// pigged out
				if (newRoll == 0) {
					System.out.println(player.getName() + " pigged out.");
					return;
				// add points
				} else {
					turnPoints += newRoll;
					System.out.println(player.getName() + " now has " + (player.getPoints() + turnPoints) + " points. (+" + turnPoints + ")");
				}
			// choice to pass
			} else {
				player.addPoints(turnPoints);
				return;
			}
		}
	}
}
