import java.util.ArrayList;

public abstract class EntityType {

	protected String name;
	private int id;
	protected int health;
	protected int focus;
	protected GameDice dice;

	public EntityType(int id, String name, int health, int focus) {
		this.id = id;
		this.health = health;
		this.focus = focus;
		this.name = name;
		this.dice = new GameDice();
	}

	// TODO: HOW DO WE ENGAGE PLAYERS IN BATTLE?!


	// Generally useful methods. 

	public abstract ArrayList<Integer> attack(int targetCount); 


	// EXCLUSIVE TO WARRIORS. TODO: MOVE TO WARRIOR() CLASS. 
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

		// 0 if alive, 1 if dead, 2 if parrying. 
		if ( this.isAlive() ) { return 0; }
		return 1;

	};

	public boolean isAlive() { return this.health > 0; }

	// At the end of every turn, each entity is entitled to some form of recouperation. 
	// 		The form of this recouperation is determined later in class definitions. 
	public abstract void replenish();

	@Override
	public String toString() {

		// Format: "#{name} has focus :  #{focus} 
		//			#{name} has health :  #{health}"
		return this.name + " has focus :  " + Integer.toString(this.focus) + "\n" + this.name + " has health:  " + Integer.toString(this.health) + "\n";

	}

	// Relevant getters and setters.
	protected int getID(){ return this.id; }

	public String getName() {return this.name;}

	// Replace this with a more appropriate format in attack()? [ALMOST CERTAINLY YES. TODO .] 
	//     {May now have been done, but is not being used! See attack() methods and the Attack() class. }
	protected abstract int getDamageDealt();

	// TODO: fix me! Get attack successes from the attack! Modify attack() methods! Modify Battle() class!
	protected boolean rollParry(int attackSuccesses) { return false; } // This is currently overridden by the PlayerType() class. 

	protected boolean rollParry() {return false;} // NOTE: SOME CLASSES @OVERRIDE THIS. 

}