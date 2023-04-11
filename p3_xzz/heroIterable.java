import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class heroIterable implements Iterable<Hero>{
	List <Hero> list;
	
	public heroIterable() {
		list = new ArrayList<Hero>();
	}
	
	
	public void addHero (Hero h) {
		list.add(h);
	}
	
	public void removeHero(Hero h) {
		list.remove(h);
	}
	
	public int size() {
		return list.size();
	}
	
	public Hero getHero(int index) {
		return list.get(index);
	}
	
	public boolean allDead() {
		for (Hero h: list) {
			if(h.isAlive()) {
				return false;
			}
		}
		return true;
	}
	
	public void printAlive() {
		for (int i = 0; i < list.size(); i++) {
			Hero h = list.get(i);
			if(h.isAlive()) {
				System.out.println(i + " " + h.info());
			}
		}
	}
	
	public boolean hasAlive () {
		for (int i = 0; i < list.size(); i++) {
			Hero h = list.get(i);
			if(h.isAlive()) {
				return true;
			}
		}
		return false;
	}
	
	public void print() {
		for (int i = 0; i < list.size(); i++) {
			Hero h = list.get(i);
			if (h instanceof Paladin) {
				System.out.println(i + " " + ((Paladin) h).toString());
			}
			else if (h instanceof Sorcerer) {
				System.out.println(i + " " + ((Sorcerer) h).toString());
			}
			else if (h instanceof Warrior) {
				System.out.println(i + " " + ((Warrior) h).toString());
			}
		}
	}
	
	public void printInfo() {
		for(int i = 0; i < this.size(); i++) {
			Hero h = this.getHero(i);
			System.out.println(i + " " + h.info());
		}
	}
	
	public Iterator<Hero> iterator(){
		return list.iterator();
	}
	
}
