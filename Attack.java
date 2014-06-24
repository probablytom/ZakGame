import java.util.ArrayList;
import java.util.List;

public class Attack {

	private int damage;
	private EntityType attacker;
	private int successes;
	private ArrayList<EntityType> defenders;

	// Constructor for when an enemy attacks players. 
	public Attack(Enemy attacker, List<PlayerType> defenders, int successes) {

		// Add the attackers and defenders to our instance variables.
		this.attacker = attacker;
		this.defenders = new ArrayList<EntityType>(defenders);
		this.damage = attacker.getDamageDealt();
		this.successes = successes; 

	}


	// Constructor for when a player attacks enemies.
	public Attack(PlayerType attacker, List<Enemy> defenders, int successes) {

		// Add the attackers and defenders to our instance variables.
		this.attacker = attacker;
		this.defenders = new ArrayList<EntityType>(defenders);
		this.damage = attacker.getDamageDealt();
		this.successes = successes; 

	}

	// We make sure the external code calls this method, so that it knows which entities are killed.
	public ArrayList<Integer> enact() {

		// To report back to Class Battle().
		ArrayList<Integer> killedEntities = new ArrayList<Integer>();
		
		int defenceResult;
		// Damage each defender and, if dead, add their ID to killedEntities to report back to Class Battle.
		for (EntityType defender : this.defenders) {
			defenceResult = defender.defend(this.damage, this.successes); // 2 is counter. 
			
			// If we parry and counter:
			if (defenceResult == 2) {
				// THIS ONLY WORKS IF ENEMIES NEVER COUNTER. LOOK INTO BETTER WAYS OF DOING THIS THAN TYPE CASTING. 
				
				// Set up a parry. 
				ArrayList newDefenders = new ArrayList();
				newDefenders.add(this.attacker);
				
				// Create a parry as an attack between the current attacker and the person countering, with their roles reversed (obviously).
				// Keep a record of all of the killed defenders and add their IDs to the list of IDs killed in this attack. 
				// ... (Counters are attacks called from attacks, so from the user's perspective they're nested.)
				ArrayList<Integer> killedInCounter = new Attack((PlayerType) defender, newDefenders.subList(0, newDefenders.size() - 1), defender.attack(1).get(3)).enact();
				killedEntities.addAll( killedInCounter );
			}
			if (!defender.isAlive()) {
				killedEntities.add(defender.getID());
				System.out.println(defender.getName() + " fell in battle!");
			}
		}

		return killedEntities;

	}

}
