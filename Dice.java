import java.util.Random;

public class Dice {
	
	private Random numberGen;
	private int limit;
	
	public Dice() {
		
		this.numberGen = new Random();
		this.limit = 10;
		
	}
	
	public Dice(int limit) {
		this.numberGen = new Random();
		this.limit = limit;
	}
	
	public int roll() {
		
		return this.numberGen.nextInt(limit) + 1;
		
	}
	
}
