
public class Armor extends Item {
	private int dmg;
	private int level;
	
	public Armor(String name, int value, int level, int dmg) {
		super(name, value);
		this.dmg = dmg;
		this.level = level; 
		
	}
	
	
	
	public int getDmg() {
		int mul = (int) (0.05) * level * dmg;
		int trueDmg = mul + dmg;
		return trueDmg;
	}

	public int getLevel() {
		return this.level;
	}
	
	public int getValue() {
		return super.getValue();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public String toString() {
		String ret = "Armor " + this.name + ": " + this.value + "Gold  level " + this.level + " Damage Reduction:" + this.dmg;
		return ret;
	}
}
