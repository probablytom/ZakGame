import java.util.ArrayList;


public class Warrior extends PlayerType {
	
	

	public Warrior(int id, String name, int health, int focus, int[] skills) {
		super(id, name, health, focus, skills);

	}
	

	@Override
	// TODO: Make use of the GameDice findRollSuccesses() method for finding roll successes. 
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
		// CURRENT FORMAT OF `attackResult`: <success of attack :: Boolean, no of people attacked :: Int, damage dealt :: Int, total successes :: Int>
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
		
		// Add the number of successes to `attackResult`.
		attackResult.add(totalSuccesses);
		
		
		// Format of attack: {success of attack, number of people attacked, attackDamage}
		return attackResult;
	}
	
	@Override
	// Warriors get their own defend() method so they can counter. 
	public int defend(int points, int successes) {

		// If we don't parry...
		if (!this.rollParry(successes)) {

			System.out.println(this.name + " is hurt for " + points + " points!");

			// Should the user say how much focus ought to be spent?

			// If we still have focus to spend

			while (points > 0 && this.focus > 0) {
				this.focus--;
				points--;
				points--;
			}

			while (points > 0 && this.health > 0) {
				this.health--;
				points--;
			}

		} 

		// The parry succeeded. If we have the spare focus to counter, see if we want to. 
		else if (this.focus > 0) {
			System.out.println(this.name + " parried successfully and took no damage!");
			String parryOption = "";
			do {
				System.out.println("Does " + this.name + " wish to spend a point of focus to counter? (Y/N)");
				parryOption = System.console().readLine().trim().toUpperCase();
			// If this doesn't yield a "Y" or "N", keep going. 
			} while (!(parryOption.compareTo("Y") == 0 || parryOption.compareTo("N") == 0) );
			

			if (parryOption.compareTo("Y") == 0) {
				
				// Prettify output!
				System.out.println();
				
				// Spend a point of focus. 
				this.focus--;
				
				return 2;
			} else {
				System.out.println("Chose not to counter. :-(");
			}

		}

		// 0 if alive, 1 if dead, 2 if parrying. 
		if ( this.isAlive() ) { return 0; }
		return 1;

	};
	
	
	
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
