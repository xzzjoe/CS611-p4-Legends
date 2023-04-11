import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Market {
	private List<Armor> armors;
	private List<Weapon> arsenal;
	private List<Potion> potions;
	private List<Spell> spells;
	private List<Item> items;
	
	public Market(List<Item> market) {
		
		this.armors = new ArrayList<>();
		this.arsenal = new ArrayList<>();
		this.potions = new ArrayList<>();
		this.spells = new ArrayList<>();
		this.items = new ArrayList<>();
		
		// armor -> weapon -> potion -> spell
		for (Item i : market) {
			if (i instanceof Armor) {
				armors.add((Armor) i);
			}
			else if (i instanceof Weapon) {
				arsenal.add((Weapon) i);
			}
			else if (i instanceof Potion) {
				potions.add((Potion) i);
			}
			else if (i instanceof Spell) {
				spells.add((Spell) i); 
			}
			items.add(i);
		}
	}
	
	public void trade(Hero h, Scanner myScan) {
		while(true) {
			System.out.println("Welcome to the market, would you like to buy(b) or sell(s)? Quit(q)");
			char choice = myScan.next().charAt(0);
			if(choice == 'b' || choice == 'B') {
				boolean onGoing = true;
				while(onGoing) {
					this.print();
					System.out.println("You have " + h.getGold() + " Gold.");
					System.out.println("Make your selection using the number associated with the item. q for quitting");
					if (!myScan.hasNextInt()) {
						char quit = myScan.next().charAt(0);
						if (quit == 'q') {
							break;
						}
					}
					int select = myScan.nextInt();
					if (select >= items.size()) {
						System.out.println("Does not find item, try again.");
						continue;
					}
					Item sold = items.get(select);
					if (sold.getValue() > h.getGold()) {
						System.out.println("Not enough money for the item, try again");
						continue;
					}
					h.buy(sold.getValue(), sold);
					System.out.println("Item " + sold.getName() + " bought for " + sold.getValue() + " gold.");
					System.out.println("Would you like to buy another item? (Y/N) ");
					char buy = myScan.next().charAt(0);
					if(buy == 'y'|| buy== 'Y') {
						onGoing = true;
					}
					else {

						onGoing = false;
					}
				}
				
			}
			else if (choice == 's' || choice == 'S' ) {
				h.inventory.printSell();
				System.out.println("Please input number corresponding to item to sell, q for quit");
				if(!myScan.hasNextInt()) {
					char res = myScan.next().charAt(0); 
					if (res == 'q') {
						break;
					}
				}
				int num;
				while (true) {
					num = myScan.nextInt();
					if (num < 0 || num >= h.inventory.size()) {
						System.out.println("Again!");
						continue;
					}
					break;
				}
				Item i1 = h.inventory.get(num);
				h.sell(i1);
				h.inventory.removeItem(i1);
				
			}
			else if( choice == 'q' || choice == 'Q') {
				break;
			}
			else {
				System.out.println("illegal input.");
				continue;
			}
//			System.out.println("Would you like another trade? (Y/N)");
//			choice = myScan.next().charAt(0);
//			if(choice == 'n' || choice == 'N') {
//				break;
//			}
			
		}
	}
	
	
	public void print() {
		int count = 0;
		System.out.println("Armors:");
		for(Armor a : armors) {
			System.out.println(count + " "+ a.toString());
			count ++;
		}
		System.out.println("");
		System.out.println("Weapons");
		for(Weapon w: arsenal) {
			System.out.println(count + " "+ w.toString());
			count ++;
		}

		System.out.println("");
		System.out.println("Potions:");
		for(Potion p: potions) {
			System.out.println(count + " "+ p.toString());
			count ++;
		}
		System.out.println("");
		System.out.println("Spells:");
		for(Spell s : spells) {
			System.out.println(count + " "+ s.toString());
			count ++;
		}

		System.out.println("");
		System.out.println("");
		
	}
	
}
