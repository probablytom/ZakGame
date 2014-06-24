import java.util.ArrayList;


public class Woodsman extends Enemy {

	public Woodsman(int id) {
		super(id, "Woodsman" + Integer.toString(id), 4, 0);
	}

	@Override
	// TODO: Make use of the GameDice findRollSuccesses() method for finding roll successes. 
	public ArrayList<Integer> attack(int targetCount) {

		ArrayList<Integer> attackRoll = new ArrayList<Integer>();

		// Attack algorithm

		// For Woodsmen, the stats do not change, so we know the target number is 7. 
		int targetNumber = 7;

		//No of dice to roll
		int diceToRoll = 3;
		// Working out how many targets are targeted
		int targetInput = 1;

		// Roll some dice and count how many successes we roll. 
		attackRoll = this.dice.rollDice(diceToRoll);

		int totalSuccesses = 0;
		for (Integer roll : attackRoll) {
			if (roll >= targetNumber) {
				totalSuccesses++;
			}
		}

		// To store the result
		// CURRENT FORMAT OF `attackResult`: <success of attack :: Boolean, no of people attacked :: Int, damage dealt :: Int, total successes :: Int>
		ArrayList attackResult = new ArrayList();

		// Add the success of the attack to `attackResult`.
		if (totalSuccesses >= targetInput) {
			attackResult.add(true);
		} else {
			attackResult.add(false);
		}

		// Add the number of people to attack to `attackResult`.
		attackResult.add(targetInput);

		// Add the damage dealt to each character to `attackResult`.
		attackResult.add(this.getDamageDealt());
		
		// Add the number of successes in the roll to `attackResult`.
		attackResult.add(totalSuccesses);

		// Format of return ArrayList: {attack success, no. of people to attack, attackDamage}
		return attackResult;
	}

	@Override
	protected int getDamageDealt() {

		return 6;
	}

}
