
public class Spell extends Item {
	private int mp;
	private int dmg;
	private char type;
	private int level;
//	types are char, 'i' == ice, 'f' == fire , 'l' == lightning
	
	public Spell(String name, int value, int level, int dmg, int mp,  char type) {
		super(name, value);
		this.mp = mp;
		this.dmg = dmg;
		this.level = level; 
		this.type = type;
		
	}
	
	
	
	public int getDmg() {
		int mul = (int) (0.1) * level * dmg ;
		int trueDmg = mul + dmg;
		return trueDmg;
	}
	
	public char getType() {
		return this.type;
	}
	
	public int getMP() {
		return this.mp;
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
		String ret;
		if (this.type == 'i') {
			ret = "Ice Spell " + this.name + ": "+ this.value + "Gold. does " + dmg + "damage. Required level " + this.level;
		}
		else if (this.type == 'f') {
			ret = "Fire Spell " + this.name +": "+ this.value + "Gold.does " + dmg + "damage.Required level " + this.level;
		}
		else if (this.type == 'l') {
			ret = "Lightning Spell " + this.name + ": "+ this.value + "Gold.does " + dmg + "damage. Required level " + this.level;
		}
		else {
			ret = "error. No spell found.";
		}
		return ret;
	}
}
