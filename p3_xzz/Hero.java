import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hero implements Fightable {
	static int baseHP = 600;
	static int baseMP = 300; 
	
	protected String name;
	protected int hp;
	protected int mp;
	protected int level;
	protected int xp;
	protected int strength;
	protected int dex;
	protected int agil;
	protected Inventory inventory;
	protected Armor armor;
	protected Weapon weapon;
	protected int gold;
	//extension: status
	
	public Hero(String name,int mp, int stren, int agil,int dex,int gold, int level) {
		this.name = name;
		this.level = level;
		this.strength = stren;
		this.agil = agil;
		this.dex = dex;
		this.mp = mp;
		this.level = level;
		this.gold = gold;
		this.inventory = new Inventory();
		this.hp = this.calcHP();
		this.xp = 0;
	}
		
	
	public int calcDmg() {
		int weapon = 100; //base damage
		if (this.weapon != null) {
			weapon = this.weapon.getDmg();
		}
		int trueDmg = weapon + weapon * (int)(0.01 * strength);
		return trueDmg; 
	}
	
	public int calcMagicDmg(int spell) {
		int trueDmg = spell + spell * (int)(0.0001 * dex);
		return trueDmg; 
		
	}
	
	public double calcDogde() {
		double res = Math.log(agil)/2;
		return res;
	}
	
	public int calcHP() {
		int health = baseHP +  baseHP * (int)(0.2 * level);
		return health;
	}
	
	
	public int calcMP() {
		int mana = baseMP + baseMP * (int)(0.05 * level) + (int) (0.0005 * dex);
		return mana;
	}
	
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getDex() {
		return this.dex;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getHP() {
		return this.hp;
	}
	
	public int getMP() {
		return this.mp;
	}
	
	public int getGold() {
		return this.gold; 
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean isAlive() {
		if (this.hp <= 0) {
			return false;
		}
		return true;
	}
	
	public void Attack(Fightable F) {
		System.out.println("Hero attacks for "  + this.calcDmg());
		F.takeDmg(this.calcDmg());
	}
	
	public boolean castSpell(Spell s, Monster m) {
		if(s.getLevel() > this.level) {
			System.out.println("Not enough level for this spell");
			return false;
		}
		if(s.getMP() > this.mp) {
			System.out.println("Not enough mana!");
			return false; 
		}
		
		char type = s.getType();
		if(type == 'i') {
			m.reduceDamage();
		}
		else if (type == 'f') {
			m.reduceDefense();
		}
		else if (type == 'l') {
			m.reduceDodge();
		}
		m.takeDmg(s.getDmg());
		return true;
	}
	
	
	public boolean usePotion(Potion p) {
		int type = p.getType();
		switch(type){
		case 0:
			System.out.println("HP potion consumed, " + p.getStats() + " HP recovered.");
			this.hp += p.getStats();
			//hp potion
			break;
		case 1:
			System.out.println("MP potion consumed, " + p.getStats() + " MP recovered.");
			this.mp += p.getStats();
			//mp potion
			break;
		case 2:
			System.out.println("Strength potion consumed, " + p.getStats() + " Strength gained.");
			this.strength += p.getStats();
			// stren potion
			break;
		case 3:
			System.out.println("Dexterity potion consumed, " + p.getStats() + " Dexterity gained.");
			this.dex += p.getStats();
			//dex potion
			break;
		case 4:
			System.out.println("Agility potion consumed, " + p.getStats() + " Agility gained.");
			this.agil += p.getStats();
			//agil potion
			break;
		default:
			return false;
			
		}
		return true;
	}
	
	public void takeDmg(int dmg) {
		Random rand = new Random();
		double chance = rand.nextDouble()*10;
		if (chance <= this.calcDogde() ) {
			System.out.println("Dodged!");
			return;
		}
		int reduction = 0;
		if (this.armor != null) {
			reduction = this.armor.getDmg();
		}
		int trueDmg = dmg - reduction;
		this.hp -= trueDmg;
		System.out.println( this.name + " took " + trueDmg + " damage, remaining HP " + this.hp);
	}
	
	public void gainXP(int xp) {
		System.out.println("Hero" + this.name + "gained " + xp + " in XP.");
		this.xp += xp;
		if (this.xp >= 1000) {
			level ++;
			this.xp = this.xp - 1000;
			this.hp = calcHP();
			this.mp = calcMP();
			System.out.println("Hero leveled up to " + this.level);
		}
	}
	
	public void gainGold(int gold) {
		this.gold += gold;
		System.out.println("Hero" + this.name + "gained " + gold + " gold.");
	}
	
	public void reset() {
		this.hp = calcHP();
		this.mp = calcMP();
	}
	
	public void buy(int cost, Item i) {
		if (i instanceof Weapon) {
			this.inventory.addItem((Weapon)i);
		}
		else if (i instanceof Armor) {
			this.inventory.addItem((Armor)i);
		}
		else if (i instanceof Potion) {
			this.inventory.addItem((Potion)i);
		}
		else if (i instanceof Spell) {
			this.inventory.addItem((Spell)i);
		}
		this.gold -= cost;
	}
	
	public void sell(Item i) {
		this.inventory.removeItem(i);
		this.gold += i.getValue()/2;
		System.out.println("Sold " +i.getName() + " for " + i.getValue()/2 + " Gold!");
	}
	
	public String toString() {
		String ret = this.name + " level " + this.level + " Strength " + this.strength  + " Agility " + this.agil + " Dexterity " + this.dex + " Gold " + this.gold;
		return ret;
	}
	
	public String info() {
		String ret = this.name + " level " + this.level  + " HP:" + this.hp + " MP:" + this.mp + " Status: ";
		if(this.hp >= 0) {
			ret += "Alive";
		}
		else {
			ret += "Dead";
		}
		String weaponName = (this.weapon == null) ? "null" :this.weapon.name;

		String armorName = (this.armor == null) ? "null" :this.armor.name;
		ret += " Weapon :" + weaponName + " Armor:" + armorName + " Gold:" + this.gold;
		return ret;
	}
	
	@Override
	public int makeMove(Fightable F) {
		Scanner myScan = new Scanner(System.in);
		Monster M = (Monster) F; 
		boolean passable = false; 
		boolean moveMade = false;
		while(!moveMade){
			System.out.println("What would you like to do?");
			System.out.println("");
			System.out.println("1 - Attack             2 - Cast a spell");
			System.out.println("3 - Equip a weapon     4 - Use a potion");
			int res = myScan.nextInt();
			if (res == 1 ) {
				this.Attack(M);
				moveMade = true;
			}
			else if (res == 2) {
				System.out.println("");
				System.out.println("");
				List<Spell> spells = this.inventory.getSpell();
				if (spells.size() == 0) {
					System.out.println("No spell in inventory");
					continue;
				}
				int count = 0;
				for (Spell s : spells) {
					System.out.println(count + s.toString());
					count ++;
				}
				while(!passable) {
					System.out.println("Select the potion number you want to use");
					res = myScan.nextInt();
					if(res >= spells.size()) {
						System.out.println("Try again.");
						continue; 
					}
					Spell spell = spells.get(res);
					this.inventory.removeItem(spell);
					this.castSpell(spell, M);
					moveMade = true;
					passable = true;
				}
			}

			else if (res == 3) {
				List<Weapon> weapons = this.inventory.getWeapon();
				if (weapons.size() == 0) {
					System.out.println("No weapons in inventory");

					System.out.println("");
					continue;
				}
				if (this.weapon != null) {
					System.out.println("Weapon already equipped, would you like to replace (Y/N) ");
					System.out.println(this.weapon.toString());
					char response = myScan.next().charAt(0);
					if(response == 'y') {
						this.inventory.addItem(this.weapon);
					}
				}
				while(!passable) {
					for (int i = 0; i < weapons.size(); i++ ) {
						Weapon w = weapons.get(i);
						System.out.println(i + " " + w.toString());
					}
					System.out.println("Select the weapon number you want to use");
					res = myScan.nextInt();
					if(res >= weapons.size()) {
						System.out.println("Try again.");
						continue; 
					}
					Weapon weapon = weapons.get(res);
					this.inventory.removeItem(weapon);
					this.weapon = weapon;
					moveMade = true; 
					passable = true;
				}
			}
			else if (res == 4) {
				System.out.println("");
				System.out.println("");
				List<Potion> potions = this.inventory.getPotion();
				if (potions.size() == 0) {
					System.out.println("No potion in inventory");
					continue;
				}
				int count = 0;
				for (Potion p : potions) {
					System.out.println(count + p.toString());
				}
				int num = myScan.nextInt();
				Potion p = potions.get(num);
				this.usePotion(p);
				moveMade = true;
				
			}
			else {
				System.out.println("Illegal input.");
				continue;
			}
			passable = false;
		}
//		myScan.close();
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.getClass() == obj.getClass()) {
			return this.getName() == ((Hero)obj).getName();
		}
		return false;
	}
	
	


}
