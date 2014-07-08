import java.util.ArrayList;
import java.util.List;

import Auditor.Auditor;


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

				if (this.enemies.size() > 0) {

					// We take a pause for each turn taken. 
					Thread.sleep(1500);

					// Let the user know who is taking their turn.  
					Auditor.presentLine("===========================");
					Auditor.presentLine(player.name + " takes their turn!");
					Auditor.presentLine();

					// Get attack stats from the player attacking. 
					ArrayList attack = player.attack(this.enemies.size());

					// If the attack succeeds, create a new Attack() with the player attacking the this.enemies specified. 
					if ((Boolean) attack.get(0)) {
						// Create a list of killed enemies by creating an attack scenario, enacting it, and storing the return ArrayList.

						// Set up useful variables.
						List<Enemy> attackedEnemies = this.enemies.subList( 0, (Integer) attack.get(1) );

						// Create the `Attack()`. 
						killedEnemies = new Attack(player, attackedEnemies, (Integer) attack.get(3), this.enemies).enact();	

						// Remove dead this.enemies.
						for (int i = 0; i < this.enemies.size(); i++) {

							// If the ID of the `i`th enemy is in our list of killed enemies, remove 'em. 
							if ( killedEnemies.contains( this.enemies.get(i).getID() ) ) {

								Auditor.presentLine(this.enemies.get(i).name + " falls in battle! ");
								this.enemies.remove(i--);
							
							}

						}
					}


				}

				
			}

			


			// Enemies get their turn to attack.
			for (Enemy enemy : this.enemies) {

				if (this.players.size() > 0) {

					// We take a pause for each turn taken. 
					Thread.sleep(1500);

					// Let the user know who is taking their turn.  
					Auditor.presentLine("===========================");
					Auditor.presentLine(enemy.name + " takes their turn!");
					Auditor.presentLine();

					// Get attack stats from the enemy attacking.
					ArrayList attack = enemy.attack(this.players.size());

					// If the attack succeeds, create a new Attack() with the enemy attacking the this.players specified with the number of successes returned.
					if ((Boolean) attack.get(0)) {

						// Set up useful variables.
						List<PlayerType> attackedPlayers = this.players.subList( 0, (Integer) attack.get(1) );

						// Create the `Attack()`.
						killedPlayers = new Attack(enemy, attackedPlayers, (Integer) attack.get(3), this.players).enact();	

						// Remove dead this.players. 
						for (int i = 0; i < this.players.size(); i++) {

							// If the ID of the `i`th player is in out list of killed players, remove 'em.
							if ( killedPlayers.contains( this.players.get(i).getID() ) ) {

								Auditor.presentLine(this.players.get(i).name + " falls in battle! ");
								this.players.remove(i--);

							}

						}

					}

				}

			}


			// Turn end. 

			Auditor.presentLine("===========================");
			Auditor.presentLine("Current stats:");
			Auditor.presentLine();

			// Turn has ended, so everybody relevant (NOTE: at this point, players) replenishes 1 point of focus. 
			for (PlayerType player : this.players) {
				player.replenish();

				// Print the stats for each player. 
				Auditor.presentLine(player.toString());

			}


			// We've ended a turn, so, prettify output. 
			Auditor.presentLine();

			// Do this until all the this.enemies or all the this.players are dead. 
		} while (this.players.size() != 0 && this.enemies.size() != 0);

		if (this.enemies.size() == 0) {
			Auditor.presentLine("The players won!");
		} else {
			Auditor.presentLine("The enemies won!");
		}

	}

}
