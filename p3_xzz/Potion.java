
public class Potion extends Item {
	private int use;
	private int stats;
	private int level;
	private int type;
//	Potion type: 0 : HP, 1 : MP, 2 : strength, 3 : dex, 4 : agility 
	
	public Potion(String name, int value,  int level,int stats, int type) {
		super(name, value);
		this.use = 1;
		this.stats = stats;
		this.level = level; 
		this.type = type;
	}
	
	
	
	public int getStats() {
		return this.stats;
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getUse() {
		return this.use;
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
		if (this.type == 0) {
			ret = "HP potion " + this.name + ": "+ this.value + "Gold. gains " + stats + "amount.";
		}
		else if (this.type == 1) {
			ret = "MP potion " + this.name +": "+ this.value + "Gold.gains " + stats + "amount.";
		}
		else if (this.type == 2) {
			ret = "Strength potion " + this.name  + ": "+ this.value + "Gold.gains " + stats + "amount.";
		}
		else if (this.type == 3){
			ret = "Dexterity potion " + this.name + ": "+ this.value + "Gold.gains " + stats + "amount.";
		}
		else {
			ret = "Agility potion " + this.name +": "+ this.value + "Gold.gains " + stats + "amount.";
		}
		return ret;
	}
}
