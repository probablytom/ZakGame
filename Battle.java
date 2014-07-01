import java.util.ArrayList;


public class Battle {

	protected ArrayList<PlayerType> players;
	protected ArrayList<Enemy> enemies;

	// Set a battle up.
	public Battle(ArrayList<PlayerType> players, ArrayList<Enemy> enemies) {

		this.players = players;
		this.enemies = enemies;

	}


	// Carry out a battle. Maybe make this a loop and create a private turn() method? 
	// Alternatively, how about recursive battles? Enact could be a single turn which 
	// 		...called itself at the end until it was no longer necessary, taking as many turns as necessary. 
	public void enact() throws InterruptedException {

		ArrayList<Integer> killedEnemies = new ArrayList<Integer>();
		ArrayList<Integer> killedPlayers = new ArrayList<Integer>();


		do {

			// Turn for this.players to attack.
			for (PlayerType player : this.players) {
				// Get attack stats from the player attacking. 
				ArrayList attack = player.attack(this.enemies.size());

				// If the attack succeeds, create a new Attack() with the player attacking the this.enemies specified. 
				if ((Boolean) attack.get(0)) {
					// Create a list of killed enemies by creating an attack scenario, enacting it, and storing the return ArrayList.
					killedEnemies = new Attack(player, this.enemies.subList( 0, (Integer) attack.get(1) ), (Integer) attack.get(3)).enact();	
				}
			}

			// Remove dead this.enemies.
			for (int i = 0; i < this.enemies.size(); i++) {
				// If the ID of the `i`th enemy is in our list of killed enemies, remove 'em. 
				if ( killedEnemies.contains( this.enemies.get(i).getID() ) ) {
					this.enemies.remove(i--);
				}
			}


			// Enemies get their turn to attack.
			for (Enemy enemy : this.enemies) {

				// Get attack stats from the enemy attacking.
				ArrayList attack = enemy.attack(this.players.size());

				// If the attack succeeds, create a new Attack() with the enemy attacking the this.players specified with the number of successes returned.
				if ((Boolean) attack.get(0)) {
					killedPlayers = new Attack(enemy, this.players.subList( 0, (Integer) attack.get(1) ), (Integer) attack.get(3)).enact();	
				}

			}

			// Remove dead this.players. 
			for (int i = 0; i < this.players.size(); i++) {
				// If the ID of the `i`th player is in out list of killed players, remove 'em.
				if ( killedPlayers.contains( this.players.get(i).getID() ) ) {
					this.players.remove(i--);
				}
			}


			// Turn end. 


			// Turn has ended, so everybody relevant (NOTE: at this point, players) replenishes 1 point of focus. 
			for (PlayerType player : this.players) {
				player.replenish();
			}


			// Print the stats for each player.
			for (PlayerType player : this.players) {
				System.out.println(player.toString());
			}

			// We've ended a turn, so, prettify output. 
			System.out.println();

			// Do this until all the this.enemies or all the this.players are dead. 
		} while (this.players.size() != 0 && this.enemies.size() != 0);

		if (this.enemies.size() == 0) {
			System.out.println("The players won!");
		} else {
			System.out.println("The enemies won!");
		}

	}

}
