
public class Weapon extends Item {
	private int dmg;
	private int level;
	private int hands;
	
//	level server as a multiplier
	
	
	public Weapon(String name, int value, int level, int dmg, int hands) {
		super(name, value);
		this.level = level;
		this.dmg = dmg;
		this.hands = hands;
	}

	
	public int getValue() {
		return super.getValue();
	}
	
	public String getName() {
		return super.getName();
	}
	
	public int getHands() {
		return this.hands;
	}
	
	public int getDmg() {
		int mul =(int) 0.4 * level * dmg;
		int trueDmg = dmg + mul;
		return trueDmg;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public String toString() {
		String ret = "Weapon " + this.name + ": " + this.value + "Gold level:" + this.level + " damage:" + this.dmg;
		return ret;
	}
	
	
}
