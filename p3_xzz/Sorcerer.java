
public class Sorcerer extends Hero {
	public Sorcerer(String name, int MP, int strength, int agil, int dex, int gold, int level ) {
		super(name, MP, strength, agil, dex, gold, level);
	}
	
	public String toString() {
		String ret = "Sorcerer: " + super.toString();
		return ret;
	}
}
