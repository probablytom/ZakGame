import java.util.ArrayList;
import Auditor.Auditor;


public class Counter {

	private int damage;
	private Warrior attacker; // This assumes Warrior()s are the only classes who can nvoke counter. That isn't necesessarily the case, if another class has a functionally similar skill!
	private int successes;
	private Enemy defender; // We'll only have one defender for a counter. 

	// Constructor. 
	public Counter(Warrior attacker, Enemy defender, int successes) {

		this.attacker = attacker;
		this.damage = attacker.getDamageDealt();
		this.defender = defender;
		this.successes = successes;

	}

	public ArrayList<Integer> enact() throws InterruptedException { 
		
		// Print an explanation to the user that only the person previously attacking can be countered.
		Auditor.presentLine( this.attacker.getName() + " attempts a counter attack directed toward " + this.defender.getName()  + "!");
		
		Thread.sleep(1000); // I think this is necessary? TODO: see if delay is necesary in counters through playtesting. 

		// To report back to the Attack() which called the Counter():
		ArrayList<Integer> killedEntities = new ArrayList<Integer>();

		
		// Damage the defender and, if dead, report back to Battle().
		
		int defenceResult = defender.defend(this.damage, this.successes);
		
		if (defenceResult == 1) { // If defence fails and the other character is dead: 
			killedEntities.add(defender.getID());
		}

		return killedEntities;
		
	}

}
