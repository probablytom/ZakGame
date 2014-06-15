import java.util.ArrayList;


public class Warrior extends PlayerType {
	
	

	public Warrior(int id, String name, int health, int focus, int[] skills) {
		super(id, name, health, focus, skills);

	}
	

	@Override
	public ArrayList<Integer> attack(int targetCount) {
		
		ArrayList<Integer> attackRoll = new ArrayList<Integer>();
		
		// Attack algorithm
		int targetNumber = 10;
		System.out.printf("What would you like to do? \n"
				+ "A : Attack with sword:   \t(Skill: " + this.getSwords() + ")\n"
				+ "B : Attack with crossbow \t(Skill: " + this.getCrossbows() + ")\n");
		String attackChoice = System.console().readLine();
		
		attackChoice = attackChoice.toUpperCase().trim();
		if (attackChoice.compareTo("A") == 0) {
			targetNumber -= this.getSwords();
		} else if (attackChoice == "B") {
			targetNumber -= this.getCrossbows();
		}
		

		// Prettify output!
		System.out.println();
		
		
		// More processing
		//No of dice to roll
		int diceToRoll = this.getDex();
		// Working out how many targets are targeted
		int targetInput = 0;
		// Find number of targets to hit
		do {
			System.out.printf("How many woodsmen would you like to hit? Please enter a number between: 1 and " + targetCount + "\n");
			String targetInputString = System.console().readLine();
			targetInput = Integer.parseInt(targetInputString);
		} while (targetInput > targetCount || targetInput <= 0);
		

		// Prettify output!
		System.out.println();
		
		// Roll the dice!
		attackRoll = this.dice.rollDice(diceToRoll);
		
		// Count the successes. 
		int totalSuccesses = 0;
		for (Integer roll : attackRoll) {
			if (roll.compareTo(targetNumber) > -1) {
				totalSuccesses++;
			}
		}
		
		// To store the result
		ArrayList attackResult = new ArrayList();
		
		
		// Add the success of the attack to `attackResult`.
		if (totalSuccesses >= targetInput) {
			attackResult.add(true);
			System.out.println(this.name + " expertly cleaves his enemies in twain!");
		} else {
			attackResult.add(false);
			System.out.println(this.name + " fumbles and fails their attack!");
		}
		
		// Prettify output!
		System.out.println();
		
		// Add the number of people attacked to `attackResult`.
		attackResult.add(targetInput);
		
		// Add the damage dealt to `attackResult`.
		attackResult.add(this.getDamageDealt());
		
		
		// Format of attack: {success of attack, number of people attacked, attackDamage}
		return attackResult;
	}
	
	
	
	// Simplified ways to get details from our ArrayList of skills. 
	public int getSwords() {
		return this.skills[7];
	}
	
	public int getCrossbows() {
		return this.skills[8];
	}
	
	public int getDaggers() {
		return this.skills[9];
	}
	
	public int getHeavy() {
		return this.skills[10];
	}
	
	public int getIntercept() {
		return this.skills[11];
	}
	
	public int getCounter() {
		return this.skills[12];
	}


	@Override
	protected int getDamageDealt() {

		// Let's let Gomez deal 6 damage for now. 
		return 6;
	}

	
	
}
