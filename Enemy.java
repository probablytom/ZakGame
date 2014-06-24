import java.util.ArrayList;


public abstract class Enemy extends EntityType {

	public Enemy(int id, String name, int health, int focus){
		super(id, name, health, focus);
	}

	public void replenish() {
		// Do nothing. 
	}
	
	
}
