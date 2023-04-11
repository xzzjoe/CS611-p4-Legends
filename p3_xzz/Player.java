import java.util.Scanner;

public class Player {
	private int r;
	private int c;
	private heroIterable heroes;
	
	public Player() {
		heroes = new heroIterable();
	}
	
	public void setPos(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	public heroIterable getHeroes() {
		return this.heroes;
	}
	
	public void recruit (Hero h) {
		this.heroes.addHero(h);
	}
	
	public void remove (Hero h) {
		this.heroes.removeHero(h);
	}
	
	public Hero heroSelect(Scanner myScan) {
		this.heroes.printInfo();
		Hero h;
		while(true) {
			System.out.println("Please select a hero based on number.");
			int num = myScan.nextInt();
			if(num < this.heroes.size()) {
				h = this.heroes.getHero(num);
				break;
			}
			System.out.println("Please provide a valid number.");
			
		}
		return h;
	}
	
	public void printInfo() {
		this.heroes.printInfo(); 
	}
	
	public void printAlive() {
		this.heroes.printAlive();
	}
	
	public boolean hasAlive() {
		return this.heroes.hasAlive();
	}
	
	public int row(){
		return this.r;
	}
	
	public int col() {
		return this.c;
	}
	
}
