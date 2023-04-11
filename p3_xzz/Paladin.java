
public class Paladin extends Hero {
	public Paladin(String name, int MP, int strength, int agil, int dex, int gold, int level ) {
		super(name, MP, strength, agil, dex, gold, level);
	}
	
	public String toString() {
		String ret = "Paladin: " + super.toString();
		return ret;
	}
}
