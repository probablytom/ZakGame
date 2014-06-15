import java.util.ArrayList;


public abstract class PlayerType extends EntityType {
	
	protected int[] skills;
	
	public PlayerType(int id, String name, int health, int focus, int[] skills) {
		super(id, name, health, focus);
		this.setSkills(skills);
	}

	@Override
	public ArrayList<Integer> attack(int targetCount) {
		// TODO Auto-generated method stub
		ArrayList<Integer> attackResult = new ArrayList<Integer>();
		return attackResult;
		
	}

	public int[] getSkills() {
		return skills;
	}

	private void setSkills(int[] skills) {
		this.skills = skills;
	}
	
	public int getStr() {
		return this.skills[0];
	}
	
	public int getDex() {
		return this.skills[1];
	}
	
	public int getWill() {
		return this.skills[2];
	}
	
	public int getStamina() {
		return this.skills[3];
	}
	
	public int getBrawn() {
		return this.skills[4];
	}
	
	public int getReflex() {
		return this.skills[5];
	}
	
	public int getAlertness() {
		return this.skills[6];
	}
	
	public int getBackstorySkill1() {
		return this.skills[13];
	}
	
	public int getBackstorySkill2() {
		return this.skills[14];
	}

	
}
