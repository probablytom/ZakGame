import java.util.ArrayList;


public abstract class Enemy extends EntityType {

	public Enemy(int id, String name, int health, int focus){
		super(id, name, health, focus);
	}

	@Override
	public abstract ArrayList<Integer> attack(int targetCount);
	
}
