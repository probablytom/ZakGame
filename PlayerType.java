import Auditor.Auditor;



public abstract class PlayerType extends EntityType {
	
	protected int[] skills;
	
	public PlayerType(int id, String name, int health, int focus, int[] skills) {
		super(id, name, health, focus);
		this.setSkills(skills);
	}
	
	public void replenish() {
		if (this.focus < 8) {
			this.focus++;
			Auditor.presentLine(this.name + " replenishes one point of focus!");
		}
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
	
	@Override
	// For some reason, this isn't working. 
	//TODO: Make this work. 
	protected boolean rollParry(int attackSuccesses) {
		
		int parrySuccesses = this.dice.countRollSuccesses(this.getDex(), 10 - this.getReflex());
		
		// Discover whether the parry was successful.
		boolean parrySuccess = parrySuccesses > attackSuccesses; // TODO: Playtesting will confirm whether we need to make this an equal nuber of successes or more. TEST!!!
		
		// Tell the user what happens. 
		if (parrySuccess) {
			Auditor.presentLine(this.name + " successfully parried his assailant's attack!");
		}
		
		// Return the result.
		return parrySuccess;
		
	}

	
}
