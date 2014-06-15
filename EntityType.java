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
	
	
	// Generally useful methods. 

	public abstract ArrayList<Integer> attack(int targetCount); // How do we interact with battles? (May be done! See the attack() class and the Battle() class. )

	// For damage actions?
	//protected  abstract void kill();

	public boolean defend(int points) {

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

		// If the character is dead, return false. 
		if (this.health < 1) {
			return false;
		} else {
			return true;
		}


	};
	
	public boolean isAlive() { return this.health > 0; }
	
	@Override
	public String toString() {
		
		return this.name + " has focus :  " + Integer.toString(this.focus) + "\n" + this.name + " has health:  " + Integer.toString(this.health) + "\n";
		
	}

	// Relevant getters and setters.
	protected int getID(){ return this.id; }
	
	public String getName() {return this.name;}
	
	// Replace this with a more appropriate format in attack()? [ALMOST CERTAINLY YES. TODO .] 
	//     {May now have been done, but is not being used! See attack() methods and the Attack() class. }
	protected abstract int getDamageDealt();

	
	


	// Better to use basic statements for these rather than function calls?

	/*private int getFocus() {return this.focus;}
	private void setFocus(int newFocus) {this.focus = newFocus;}

	private int getHealth() {return this.health;}
	private void setHealth(int newHealth) { this.health = newHealth; }*/
}
