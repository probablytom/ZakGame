import java.util.ArrayList;

import Auditor.Auditor;

/**
 * 
 * @author tom
 * @version 0.0.1
 * 
 * @note Things to note: In a x/y roll, x is one of str, dex and will (or int, depending on what we call it!)
 *
 */

public class Game {

	private PlayerType player; // Only one protagonist for now.
	private int characterCount; // So we can assign unique id numbers to game entities.
	private ArrayList<PlayerType> players;
	private ArrayList<Enemy> enemies;
	private ArrayList<Dice> gameDice;
	
	public Game() {
		
		Auditor.addLine("Created game.");
		
		// Create variables
		characterCount = 0;
		this.gameDice = new ArrayList<Dice>();
		this.players = new ArrayList<PlayerType>();
		this.enemies = new ArrayList<Enemy>();
		
		// Populate dice array
		for (int i = 0; i < 7; i++) {
			this.gameDice.add(new Dice());
		}
		
		// Create game entities
		createPlayer();
		
		Auditor.addLine("Created Gomez1.");
		
	}
	
	public void testBattle() throws InterruptedException {
		
		Auditor.addLine("Testing battle mechanics.");
		
		// Find out how many woodsmen Gomez faces.
		
		int woodsmen = 0;
		do {
			System.out.println("How many Woodsmen?");
			String woodsmenIn = System.console().readLine();
			try {
				woodsmen = Integer.parseInt(woodsmenIn);
			} catch (Exception e) {
				System.out.println("Enter a valid integer!");
			}
		} while (woodsmen == 0);
		
		// Prettify output!
		System.out.println();
		
		
		// Create said woodsmen.
		for (int i = 0; i < woodsmen; i++) {
			this.createWoodsman();
		}
		
		Auditor.addLine("Gomez1 is going into battle with " + Integer.toString(woodsmen) + " woodsmen.");
		
		Battle battle = new Battle(this.players, this.enemies);	
		battle.enact();
		
		Auditor.addLine("Battle completed!");
		
	}

	private void createPlayer(){
		
		int[] gomezSkills = {2, 4, 1, 2, 0, 3, 0, 3, 1, 2, 1, 1, 3, 0, 0};
		this.player = new Warrior(characterCount++, "Gomez" + this.characterCount++, 8, 8, gomezSkills);
		this.players.add(this.player);
		
	}
	
	private void createWoodsman() {
		this.enemies.add( new Woodsman(characterCount++) );
	}

}