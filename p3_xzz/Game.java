import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	public List<Item> marketList;
	public List<Paladin> paladins;
	public List<Sorcerer> sorcerers;
	public List<Warrior> warriors;
	public List<Monster> monsters;
	public heroIterable heroes;
	public Board b;
	
	
	public Game() {
		marketList = new ArrayList<>();
		monsters = new ArrayList<>();
		paladins = new ArrayList<>();
		sorcerers = new ArrayList<>();
		warriors = new ArrayList<>();
		heroes = new heroIterable();
		this.b = null;
	}
	
	public static void equip(Scanner myScan, Hero h) {
		System.out.println("Equip armor(a) or weapon (w)");
		char choice = myScan.next().charAt(0);
		if (choice == 'w') {
			boolean passable = false;
			List<Weapon> weapons = h.inventory.getWeapon();
			if (weapons.size() == 0) {
				System.out.println("No weapons in inventory");
				System.out.println("");
				return;
			}
			if (h.weapon != null) {
				System.out.println("Weapon already equipped, would you like to replace (Y/N) ");
				System.out.println(h.weapon.toString());
				char response = myScan.next().charAt(0);
				if(response == 'y') {
					h.inventory.addItem(h.weapon);
				}
				return;
			}
			while(!passable) {
				for (int i = 0; i < weapons.size(); i++ ) {
					Weapon w = weapons.get(i);
					System.out.println(i + " " + w.toString());
				}
				System.out.println("Select the weapon number you want to use");
				int res = myScan.nextInt();
				if(res >= weapons.size()) {
					System.out.println("Try again.");
					continue; 
				}
				Weapon weapon = weapons.get(res);
				h.inventory.removeItem(weapon);
				h.weapon = weapon;
				passable = true;
			}
		}
		else if (choice == 'a') {
			boolean passable = false;
			List<Armor> armors = h.inventory.getArmor();
			if (armors.size() == 0) {
				System.out.println("No armor in inventory");
				System.out.println("");
				return;
			}
			if (h.armor != null) {
				System.out.println("Armor already equipped, would you like to replace (Y/N) ");
				System.out.println(h.armor.toString());
				char response = myScan.next().charAt(0);
				if(response == 'y') {
					h.inventory.addItem(h.armor);
				}
				return; 
			}
			while(!passable) {
				for (int i = 0; i < armors.size(); i++ ) {
					Armor a = armors.get(i);
					System.out.println(i + " " + a.toString());
				}
				System.out.println("Select the armor number you want to use");
				int res = myScan.nextInt();
				if(res >= armors.size()) {
					System.out.println("Try again.");
					continue; 
				}
				Armor armor = armors.get(res);
				h.inventory.removeItem(armor);
				h.armor = armor;
				passable = true;
			}
		}
		else {
			System.out.println("Unrecognizable command");
		}
		
	}
	
	public static void main(String[] args) throws IOException {

		Game g = new Game();
		String dir = System.getProperty("user.dir") + "/data" ;
		// TODO Auto-generated method stub
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("data/Armory.txt"));
		String line;
		reader.readLine();
		line = reader.readLine();
		
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Armor curr = new Armor(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]));
			g.marketList.add(curr);
			line = reader.readLine();
		}
		reader.close();
		reader = new BufferedReader(new FileReader("data/Weaponry.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Weapon curr = new Weapon(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]));
			g.marketList.add(curr);
			line = reader.readLine();
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader("data/Potions.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Potion curr = new Potion(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]));
			g.marketList.add(curr);
			line = reader.readLine();
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader("data/FireSpells.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Spell curr = new Spell(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]), 'f');
			g.marketList.add(curr);
			line = reader.readLine();
		}
		reader.close();
		
		
		reader = new BufferedReader(new FileReader("data/IceSpells.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Spell curr = new Spell(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]),'i');
			g.marketList.add(curr);
			line = reader.readLine();
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader("data/LightningSpells.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Spell curr = new Spell(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]), 'l');
			g.marketList.add(curr);
			line = reader.readLine();
		}
		reader.close();

		reader = new BufferedReader(new FileReader("data/Dragons.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Monster curr = new Monster(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]));
			g.monsters.add(curr);
			line = reader.readLine();
		}
		reader.close();
		
		
		reader = new BufferedReader(new FileReader("data/Exoskeletons.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Monster curr = new Monster(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]));
			g.monsters.add(curr);
			line = reader.readLine();
		}
		reader.close();
		
		
		reader = new BufferedReader(new FileReader("data/Spirits.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Monster curr = new Monster(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]));
			g.monsters.add(curr);
			line = reader.readLine();
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader("data/Paladins.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Paladin curr = new Paladin(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]), Integer.valueOf(delimit[5]), Integer.valueOf(delimit[6]));
			g.paladins.add(curr);
			g.heroes.addHero(curr);
			line = reader.readLine();
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader("data/Sorcerers.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Sorcerer curr = new Sorcerer(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]), Integer.valueOf(delimit[5]), Integer.valueOf(delimit[6]));
			g.sorcerers.add(curr);
			g.heroes.addHero(curr);
			line = reader.readLine();
		}
		reader.close();
		
		reader = new BufferedReader(new FileReader("data/Warriors.txt"));
		reader.readLine();
		line = reader.readLine();
		while(line != null) {
			String [] delimit = line.split("\\s+");
			Warrior curr = new Warrior(delimit[0], Integer.valueOf(delimit[1]), Integer.valueOf(delimit[2]),Integer.valueOf(delimit[3]), Integer.valueOf(delimit[4]), Integer.valueOf(delimit[5]), Integer.valueOf(delimit[6]));
			g.warriors.add(curr);
			g.heroes.addHero(curr);
			line = reader.readLine();
		}
		reader.close();
		
		Scanner myScan = new Scanner(System.in);
		System.out.println("Welcome to Monsters and Legends! V1");

		boolean satisfied = false;
		Board b;
		Player p = new Player();
		System.out.println("Please indicate you desired board size in form or (row col)");
		int r = myScan.nextInt();
		int c = myScan.nextInt();
		while (!satisfied) {
			b = new Board(r,c);
			b.createWorld(g.monsters, g.marketList, p);
			b.printBoard();
			System.out.println("@ is where you spawn, M is monster, $ is market, | is inaccessible space, all else are empty space");
			System.out.println("Are you satisfied with the board? (Y/N)");
			char choice = myScan.next().charAt(0);
			if(choice == 'y' || choice == 'Y') {
				satisfied = true;
				g.b = b;
			}
		}
		int numHero; 
		while(true) {
			System.out.println("How many heroes would you like to have? (max 3)");
			numHero = myScan.nextInt();
			if( numHero > 3 || numHero <= 0) {
				System.out.println("Please type a number from 1 - 3");
				continue; 
			}
			break;
		}
		g.heroes.print();
		int numSelected = 0;
		while(numSelected < numHero) {
			System.out.println("Select hero based on the number associated.");
			int select = myScan.nextInt();
			if (select >= g.heroes.size()) {
				System.out.println("Please provide a valid selection");
				g.heroes.print();
				continue;
			}
			Hero curr = g.heroes.getHero(select); 
			System.out.println(curr.getName() + " is recruited.");
			p.recruit(curr);
			numSelected ++;
		}
		boolean gaming = true;
		while(gaming) {
			g.b.printBoard();
			printHelp();
			Tile currT = g.b.tiles.get(p.row()).get(p.col());
			if(currT instanceof MonsterTile) {
				if (((MonsterTile) currT).isOccupied()) {
					Monster monster = ((MonsterTile) currT).getMonster();
					System.out.println("Encounter " + monster.getName() + " !");

					boolean fighting = true;
					while(fighting) {
						System.out.println("Select an alive hero for combat");
						Hero h = p.heroSelect(myScan);
						Battle battle = new Battle(h, monster);
						boolean res = battle.start();
						if (!res) {
							if(!p.hasAlive()) {
								fighting=false;
								System.out.println("Game Over!");
								gaming = false;
							}
							continue;
						}
						((MonsterTile) currT).clearMonster();
						fighting = false;
					}
				}
				
			}
			else if(currT instanceof MarketTile) {
				System.out.println("Welcome to the market, Would you like to come in? (y/n)");
				char enter = myScan.next().charAt(0);
				if (enter == 'y') {
					Market mkt = ((MarketTile) currT).getMarket();
					boolean shopping = true;
					while (shopping) {
						Hero h = p.heroSelect(myScan);
						mkt.trade(h, myScan); 
						System.out.println("Would you like to keep trading? (Y/N)");
						char choice = myScan.next().charAt(0);
						if (choice == 'n' || choice == 'N') {
							shopping = false;
						}
						
					}
				}
				
			}
			if (gaming) {
				System.out.println("Type in your command");
				char cmd = myScan.next().charAt(0);
				if (cmd == 'w' || cmd == 'W') {
					int row = p.row();
					if (row -1 < 0) {
						System.out.println("can't move up");
						continue;
					}
					Tile newT = g.b.tiles.get(row - 1).get(p.col());
					if(newT instanceof TreeTile) {
						System.out.println("Tree Tile: can't move up");
						continue; 
					}
					p.setPos(row-1, p.col());
					currT.clearOccupied();
					newT.setOccupied();
				}
				else if (cmd == 'a' || cmd == 'A') {
					int col = p.col();
					if (col - 1 < 0) {
						System.out.println("can't move left.");
						continue;
					}
					Tile newT = g.b.tiles.get(p.row()).get(col -1);
					if(newT instanceof TreeTile) {
						System.out.println("Tree Tile: can't move left");
					}
					p.setPos(p.row(), col -1);
					currT.clearOccupied();
					newT.setOccupied();
				}
				else if (cmd == 's' || cmd == 'S') {
					int row = p.row();
					if (row + 1 >= g.b.getRow()) {
						System.out.println("can't move down");
						continue;
					}
					Tile newT = g.b.tiles.get(row + 1).get(p.col());
					if(newT instanceof TreeTile) {
						System.out.println("Tree Tile: can't move down");
						continue;
					}
					p.setPos(row+1, p.col());
					currT.clearOccupied();
					newT.setOccupied();
				}
				else if (cmd == 'd' || cmd == 'D') {
					int col = p.col();
					if (col + 1 >= g.b.getCol()) {
						System.out.println("can't move right.");
						continue;
					}
					Tile newT = g.b.tiles.get(p.row()).get(col + 1);
					if(newT instanceof TreeTile) {
						System.out.println("Tree Tile: can't move right");
						continue;
					}
					p.setPos(p.row(), col + 1);
					currT.clearOccupied();
					newT.setOccupied();
				}
				else if (cmd == 'e' || cmd == 'E') {
					System.out.println("Which hero to equip?");
					Hero h = p.heroSelect(myScan);
					equip(myScan,h);
				}
				else if (cmd == 'i' || cmd == 'I') {
					p.printInfo();
				}
				else if (cmd == 'q' || cmd == 'Q') {
					break;
				}	
				else if (cmd == 'h' || cmd == 'H') {
					g.b.printBoard();
					printHelp();
				}
			}
			
			if(!p.hasAlive()) {
				break;
			}
		}
		System.out.println("Thanks for playing. See you next time!");
		myScan.close();
	}
	
	
	static public void printHelp() {
		System.out.println("Command interface");
		System.out.println("w/W move up");
		System.out.println("s/S move down");
		System.out.println("a/A move left");
		System.out.println("d/D move right");
		System.out.println("i/I Current hero info");
		System.out.println("h/H help");
		System.out.println("e/E equip");
		System.out.println("q/Q quit game");

	}

}
