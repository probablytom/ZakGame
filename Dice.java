import java.util.Random;

public class Dice {
	
	private Random numberGen;
	private int faces;
	
	public Dice() {
		
		this.numberGen = new Random();
		this.faces = 10;
		
	}
	
	public Dice(int faces) {
		this.numberGen = new Random();
		this.faces = faces;
	}
	
	public int roll() {
		
		return this.numberGen.nextInt(faces) + 1;
		
	}
	
}
