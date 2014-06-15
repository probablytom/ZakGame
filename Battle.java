import java.util.ArrayList;


public class Battle {

	public Battle(ArrayList<PlayerType> players, ArrayList<Enemy> enemies) {
		do {
			
			// Turn for players to attack.
			for (PlayerType player : players) {
				// Get attack stats from the player attacking. 
				ArrayList attack = player.attack(enemies.size());
				
				// If the attack succeeds, create a new Attack() with the player attacking the enemies specified. 
				if ((Boolean) attack.get(0)) {
					new Attack(player, enemies.subList( 0, (Integer) attack.get(1) ));	
				}
			}
			
			// Remove dead enemies.
			for (int i = 0; i < enemies.size(); i++) {
				if (!enemies.get(i).isAlive()) {
					System.out.println(enemies.get(i).getName() + " fell in battle!");
					enemies.remove(i--);
				}
			}
			
			
			// Enemies get their turn to attack.
			for (Enemy enemy : enemies) {

				// Get attack stats from the enemy attacking.
				ArrayList attack = enemy.attack(players.size());

				// If the attack succeeds, create a new Attack() with the enemy attacking the players specified.
				if ((Boolean) attack.get(0)) {
					new Attack(enemy, players.subList( 0, (Integer) attack.get(1) ));	
				}

			}
			
			// Remove dead players. 
			for (int i = 0; i < players.size(); i++) {
				if (!players.get(i).isAlive()) {
					System.out.println(players.get(i).getName() + " fell in battle!");
					players.remove(i--);
				}
			}
			
			
			// Print the stats for each player.
			for (PlayerType player : players) {
				System.out.println(player.toString());
			}

		// Do this until all the enemies or all the players are dead. 
		} while (players.size() != 0 && enemies.size() != 0);
		
		if (enemies.size() == 0) {
			System.out.println("The players won!");
		} else {
			System.out.println("The enemies won!");
		}
		
	}

}
