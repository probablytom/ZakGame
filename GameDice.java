import java.util.ArrayList;

public class GameDice {

	private Dice dice;
	
	// Constructor. Currently only doing d10... okay for now?
	public GameDice() {
		
		this.dice = new Dice();
		
	}
	
	public ArrayList rollDice(int noOfDice) {
		
		ArrayList<Integer> results = new ArrayList<Integer>();
		
		if (noOfDice > 0) {
			for (int i = 0; i < noOfDice; i++) {
				results.add(dice.roll());
			}
		}
		return results;
	}
	
}
