import java.util.ArrayList;

public class GameDice {

	private Dice dice;
	
	// Constructor. Currently only doing d10... okay for now?
	public GameDice() {
		
		this.dice = new Dice();
		
	}
	
	public ArrayList<Integer> rollDice(int noOfDice) {
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		if (noOfDice > 0) {
			for (int i = 0; i < noOfDice; i++) {
				results.add(dice.roll());
			}
		}
		return results;
	}
	
	
	public ArrayList<Boolean> findRollSuccesses(int noOfDice, int targetNumber) {
		
		// To store values to return.
		ArrayList<Boolean> successes = new ArrayList<Boolean>();
		
		// Roll dice.
		ArrayList<Integer> diceRoll = this.rollDice(noOfDice);
		
		// Calculate successes.
		for (Integer roll : diceRoll) {
			if ( roll.compareTo(targetNumber) > -1 ) {
				successes.add(true);
			} else {
				successes.add(false);
			}
		}
		
		// Return our values.
		return successes;
	}
	
	public int countRollSuccesses(int noOfDice, int targetNumber) {
		
		// Count the successes in a roll from this.findRollSuccesses.
		ArrayList<Boolean> diceRoll = this.findRollSuccesses(noOfDice, targetNumber);
		
		// To count the successes. 
		int successes = 0;
		
		for (boolean roll : diceRoll) {
			if (roll) { successes++; }
		}
		
		return successes;
		
	}
	
}
