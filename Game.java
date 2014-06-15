import java.util.ArrayList;

public class Game {

	private PlayerType player; // Only one protagonist for now.
	private int characterCount; // So we can assign unique id numbers to game entities.
	private ArrayList<EntityType> entities; // To keep track of entities who play within the game.
	private ArrayList<PlayerType> players;
	private ArrayList<Enemy> enemies;
	private ArrayList<Dice> gameDice;
	
	public Game() {
		
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
		battle();
		
	}
	
	public void battle() {
		
		
		
		//TODO: Parrying. 
		
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
		
		
		Battle battle = new Battle(this.players, this.enemies);	
		
	}

	private void createPlayer(){
		
		int[] gomezSkills = {2, 4, 1, 2, 0, 0, 0, 3, 1, 2, 1, 1, 3, 0, 0};
		this.player = new Warrior(characterCount++, "Gomez", 8, 8, gomezSkills);
		this.players.add(this.player);
		
	}
	
	public void createWoodsman() {
		this.enemies.add( new Woodsman(characterCount++) );
	}

}