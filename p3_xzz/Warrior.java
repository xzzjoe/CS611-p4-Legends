
public class Warrior extends Hero {
	public Warrior(String name, int MP, int strength, int agil, int dex, int gold, int level ) {
		super(name, MP, strength, agil, dex, gold, level);
	}
	
	public String toString() {
		String ret = "Warrior: " + super.toString();
		return ret;
	}
}
