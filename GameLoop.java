// TODO: USE THIS CLASS PROPERLY!!

import Auditor.*;

public class GameLoop {
	
	public void main() {
	}
	
	public static void main(String args[]) {
		
		// Attempt to run the program.
		try {
			new GameLoop();
		} catch (InterruptedException e) {
			// If the program is interrupted at all:
			System.out.println();
			System.out.println("Interrupted!");
			System.out.println("Just in case that wasn't intentional:");
			System.out.println();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public GameLoop() throws InterruptedException{
		Game game = new Game();
		game.testBattle();
		Auditor.dumpLog();
	}
	
}
