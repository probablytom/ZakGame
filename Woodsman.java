import java.util.ArrayList;


public class Woodsman extends Enemy {
	
	public Woodsman(int id) {
		super(id, "Woodsman" + Integer.toString(id), 4, 0);
	}

	@Override
	public ArrayList<Integer> attack(int targetCount) {
				ArrayList<Integer> attackRoll = new ArrayList<Integer>();
				
				// Attack algorithm
				int targetNumber = 7;
				/*System.out.printf("What would you like to do? \n"
						+ "A : Attack with sword:  " + this.getSwords()
						+ "B : Attack with crossbow" + this.getCrossbows());
				String attackChoice = System.console().readLine();*/
				
				/*attackChoice.toUpperCase();
				attackChoice.trim();
				if (attackChoice == "A") {
					targetNumber -= this.getSwords();
				} else if (attackChoice == "B") {
					targetNumber -= this.getCrossbows();
				}*/
				
				// More processing
				//No of dice to roll
				int diceToRoll = 3;
				// Working out how many targets are targeted
				int targetInput = 1;
				// Find number of targets to hit
				/*do {
					System.out.printf("How many woodsmen would you like to hit? Please enter a number between: 1 and " + targetCount);
					String targetInputString = System.console().readLine();
					targetInput = Integer.parseInt(targetInputString);
				} while (targetInput > targetCount || targetInput <= 0);*/
				
				attackRoll = this.dice.rollDice(diceToRoll);
				
				int totalSuccesses = 0;
				for (Integer roll : attackRoll) {
					if (roll >= targetNumber) {
						totalSuccesses++;
					}
				}
				
				// To store the result
				ArrayList attackResult = new ArrayList();
				
				// Add the success of the attack to `attackResult`.
				if (totalSuccesses >= targetInput) {
					attackResult.add(true);
				} else {
					attackResult.add(false);
				}
				
				// Add the number of people to attack to `attackResult`
				attackResult.add(targetInput);
				
				// Add the damage dealt to each character to `attackResult`
				attackResult.add(this.getDamageDealt());
				
				// Format of return ArrayList: {attack success, no. of people to attack, attackDamage}
				return attackResult;
	}

	@Override
	protected int getDamageDealt() {
		// TODO Auto-generated method stub
		return 6;
	}

	

}
