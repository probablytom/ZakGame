import java.util.ArrayList;
import java.util.List;
import Auditor.Auditor;

public class Attack {

	private int damage;
	private EntityType attacker;
	private int successes;
	private ArrayList<EntityType> defenders;
	private List<EntityType> bystanders;

	// Constructor for when an enemy attacks players. 
	public Attack(Enemy attacker, List<PlayerType> defenders, int successes, List bystanders) {

		// Add the attackers and defenders to our instance variables.
		this.attacker = attacker;
		this.defenders = new ArrayList<EntityType>(defenders);
		this.damage = attacker.getDamageDealt();
		this.successes = successes; 
		this.bystanders = bystanders;

	}


	// Constructor for when a player attacks enemies.
	public Attack(PlayerType attacker, List<Enemy> defenders, int successes, List bystanders) {

		// Add the attackers and defenders to our instance variables.
		this.attacker = attacker;
		this.defenders = new ArrayList<EntityType>(defenders);
		this.damage = attacker.getDamageDealt();
		this.successes = successes; 
		this.bystanders = bystanders;

	}

	// We make sure the external code calls this method, so that it knows which entities are killed.
	public ArrayList<Integer> enact() throws InterruptedException {

		// To report back to Class Battle().
		ArrayList<Integer> killedEntities = new ArrayList<Integer>();
		
		int defenceResult;
		// Damage each defender and, if dead, add their ID to killedEntities to report back to Battle().
		for (EntityType defender : this.defenders) {


			// See whether anybody wants to intercept the attack, and if so, don't deal damage. 
			boolean intercepted = false;
			for (EntityType interceptee : this.bystanders) {
				if ( ( interceptee.canIntercept() ) && ( interceptee.getID() != defender.getID() ) ) {
					if (interceptee.intercept(this.attacker, defender, successes) ) {
					// Testing intercept
						Auditor.presentLine(interceptee.name + " successfully intercepts!");
						intercepted = true;
					}
				}
			}
			
			

			// If there's no interception, deal damage: 
			if ( !(intercepted) ) {
				defenceResult = defender.defend(this.damage, this.successes); // 2 is counter. 

				// If we parry and counter:
				if (defenceResult == 2) {

					// Create a parry as an attack between the current attacker and the person countering, with their roles reversed (obviously).
					// Keep a record of all of the killed defenders and add their IDs to the list of IDs killed in this attack. 
					// ... (Counters are attacks called from attacks, so from the user's perspective they're nested.)
					ArrayList<Integer> killedInCounter = new Counter((Warrior) defender, (Enemy) this.attacker, defender.attack(1).get(3)).enact();
					killedEntities.addAll( killedInCounter );

				// If the defence failed and the defender is no longer alive:
				} else if (defenceResult == 1) {

					killedEntities.add(defender.getID());

				} else if (defenceResult == 0) {
				// Defence worked, do nothing. Included for completeness. 
				}



			}

		}
		return killedEntities;

	}

}
