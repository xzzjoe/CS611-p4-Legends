import java.util.Random;

public class Monster implements Fightable{
    private String name;
    private int hp;
    private int dmg;
    private int def;
    private int dodge;
    private int level;

    public Monster(String name, int level, int dmg, int def, int dodge) {
        this.name = name;
        this.level = level;
        this.hp = calcHP();
        this.dmg = dmg;
        this.def = def;
        this.dodge = dodge;
    }

    public int calcHP() {
    	return 400 + 50 * level;
    }
    
    public String getName() {
        return name;
    }

    
    public int getHP() {
        return hp;
    }

    public int getAttackDamage() {
        return dmg;
    }
    
    public int getLevel() {
    	return this.level;
    }
    
    public int returnDodge() {
    	return this.dodge;
    }

    public boolean isAlive() {
    	if(this.hp > 0) {
    		return true;
    	}
    	return false;
    }
    
    public void reduceDamage() {
    	this.dmg = (int) (this.dmg * 0.95);
    }

    public void reduceDefense() {
    	this.def = (int) (this.def * 0.95);
    }
    
    public void reduceDodge() {
    	this.dodge = (int) (this.dodge * 0.95);
    }
    
    public void attack(Fightable F) {
    	System.out.println("Monster attacks for " + this.dmg);
        F.takeDmg(this.dmg);
    }
    
	public void takeDmg(int dmg) {
		Random rand = new Random();
		int chance = rand.nextInt(100) + 1;
		if (chance <= (this.dodge)/2 ) {
			System.out.println("Monster dodge chance" + chance + "Monster stats " + this.dodge);
			System.out.println("Dodged!");
			return;
		}
		int trueDmg = (dmg - this.def * 0.1) > 0 ? dmg - this.def : 0;
		this.hp -= trueDmg;
		System.out.println("Monster " + this.name + " took " + trueDmg + " damage, remaining HP " + this.hp);
	}
	
	public String toString() {
		String ret = "Monster " + this.name + " level " + this.level + " damage " + this.dmg + " defence " + this.def + " dodge chance " + this.dodge;
		return ret;
	}
	
	public int expGiven() {
		return 300 + level * 100;
	}
	
	public int goldGiven() {
		return 200 + level * 50;
	}
	
	public void reset() {
		this.hp = calcHP();
	}

	@Override
	public int makeMove(Fightable F) {
		this.attack(F);
		// TODO Auto-generated method stub
		return 0;
	}
}

