import java.util.ArrayList;
import java.util.List;

public class Attack {

	//protected PlayerType attacker;
	//protected ArrayList defenders;

	// Constructor for when an enemy attacks players. 
	public Attack(Enemy attacker, List<PlayerType> defenders) {

		//this.attacker = attacker;
		//this.defenders = defenders;

		int damage = attacker.getDamageDealt();
		for (EntityType defender : defenders) {
			defender.defend(damage);
		}

	}


	// Constructor for when a player attacks enemies.
	public Attack(PlayerType attacker, List<Enemy> defenders) {
		//this.attacker = attacker;
		//this.defenders = defenders;

		int damage = attacker.getDamageDealt();
		for (EntityType defender : defenders) {
			defender.defend(damage);
		}
	}

}
