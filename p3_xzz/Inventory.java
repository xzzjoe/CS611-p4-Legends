import java.util.ArrayList;
import java.util.List;

public class Inventory{
	private List<Weapon> weapons;
	private List<Spell> spells;
	private List<Armor> armor;
	private List<Potion> potions;
	private List<Item> items;
	
	public Inventory () {
		weapons = new ArrayList<>();
		spells = new ArrayList<>();
		armor = new ArrayList<>();
		potions = new ArrayList<>();
		items = new ArrayList<>();
	}
			
	
	public boolean addItem(Item i1) {
		if (i1 instanceof Weapon) {
			weapons.add((Weapon)i1);
		}
		else if (i1 instanceof Spell) {
			spells.add((Spell)i1);
		}
		else if(i1 instanceof Armor) {
			armor.add((Armor)i1);
		}
		else if (i1 instanceof Potion){
			potions.add((Potion)i1);
		}
		items.add(i1);
		return false;
	}
	
	public boolean removeItem(Item i1) {
		items.remove(i1);
		if (i1 instanceof Weapon) {
			weapons.remove((Weapon)i1);
			return true;
		}
		else if (i1 instanceof Spell) {
			spells.remove((Spell)i1);
			return true;
		}
		else if(i1 instanceof Armor) {
			armor.remove((Armor)i1);
			return true;
		}
		else if (i1 instanceof Potion){
			potions.remove((Potion)i1);
			return true; 
		}
		return false;
	}
	
	public void removeItem(int index) {
		items.remove(index);
	}
	
	
	public List<Weapon> getWeapon() {
		weapons.clear();
		for (Item i1 : items) {
			if (i1 instanceof Weapon) {
				weapons.add((Weapon)i1);
			}
		}
		return weapons;
		
	}
	
	public List<Spell> getSpell() {
		spells.clear();
		for (Item i1 : items) {
			if (i1 instanceof Spell) {
				spells.add((Spell)i1);
			}
		}
		return spells;	
	}
	
	public List<Potion> getPotion() {
		potions.clear();
		for (Item i1 : items) {
			if (i1 instanceof Potion) {
				potions.add((Potion)i1);
			}
		}
		return potions;		
	}
	
	
	public List<Armor> getArmor() {
		armor.clear();
		for (Item i1 : items) {
			if (i1 instanceof Armor) {
				armor.add((Armor)i1);
			}
		}
		return armor;	
	}	
	
	public int size() {
		return this.items.size();
	}
	
	public Item get(int index) {
		return this.items.get(index);
	}
	
	public void print() {
		System.out.println("Weapons:");
		System.out.println("");
		List<Weapon> weapons = getWeapon();
		for (Weapon w: weapons) {
			System.out.println(w.toString());
		}
		System.out.println();
		
		System.out.println("Spells:");
		System.out.println("");
		List<Spell> spells = getSpell();
		for (Spell s: spells) {
			System.out.println(s.toString());
		}
		System.out.println();
		
		System.out.println("Potions:");
		System.out.println("");
		List<Potion> potions = getPotion();
		for (Potion p: potions) {
			System.out.println(p.toString());
		}
		System.out.println();
		
		System.out.println("Armor:");
		System.out.println("");
		List<Armor> armors= getArmor();
		for (Armor a: armors) {
			System.out.println(a.toString());
		}
		System.out.println();
	}
	
	public void printSell() {
		System.out.println();
		for (int i = 0; i < items.size(); i ++) {
			Item myItem = items.get(i);
			System.out.println(i + " " + myItem.heroSell());
		}
//		System.out.println("Weapons:");
//		System.out.println("");
//		List<Weapon> weapons = getWeapon();
//		for (Weapon w: weapons) {
//			System.out.println(w.heroSell());
//		}
//		System.out.println();
//		
//		System.out.println("Spells:");
//		System.out.println("");
//		List<Spell> spells = getSpell();
//		for (Spell s: spells) {
//			System.out.println(s.heroSell());
//		}
//		System.out.println();
//		
//		System.out.println("Potions:");
//		System.out.println("");
//		List<Potion> potions = getPotion();
//		for (Potion p: potions) {
//			System.out.println(p.heroSell());
//		}
//		System.out.println();
//		
//		System.out.println("Armor:");
//		System.out.println("");
//		List<Armor> armors= getArmor();
//		for (Armor a: armors) {
//			System.out.println(a.heroSell());
//		}
//		System.out.println();
	}
}
