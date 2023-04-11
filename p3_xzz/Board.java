
import java.util.*;

public class Board {
    public ArrayList<ArrayList<Tile>> tiles;
    private int r;
    private int c;
    private int onBoard;

    public Board(int row, int col){ 
        this.r = row;
        this.c = col;
        this.tiles = new ArrayList<>();

        this.onBoard = 0;
    }
    
    public void createWorld(List<Monster> monsters, List<Item> marketList, Player p) {
    	boolean playerPlaced = false;
    	Random rand = new Random();
        for (int i = 0; i < r; i++){
            this.tiles.add(new ArrayList<>());
            for(int j = 0; j < c; j++){
            	int choice = rand.nextInt(100);
            	if (choice <= 20) {
            		MonsterTile curr = new MonsterTile(i,j);
            		curr.setMonster(monsters.get(choice));
            		this.tiles.get(i).add(curr);
            	}
            	else if (choice <= 33) {
            		TreeTile curr = new TreeTile(i,j);
            		this.tiles.get(i).add(curr);
            	}
            	else if (choice <=40){
            		MarketTile curr = new MarketTile(i,j, marketList);
            		this.tiles.get(i).add(curr);
            	}
            	else {
            		Tile curr = new Tile(i,j);
            		if(!playerPlaced) {
            			p.setPos(i, j);
            			curr.setOccupied();
            			playerPlaced  = true;
            		}
            		this.tiles.get(i).add(curr);

            		
            	}
//                this.tiles.get(i).add(curr)); 
            }
        }
    }
    
        
    public void addPiece(int i) {
    	this.onBoard += i;
    }
    
    public int getRow() {
    	return this.r;
    }
    
    public int getCol() {
    	return this.c;
    }
    
    public int getOnBoard() {
    	return this.onBoard;
    }
    
    
	public void printBoard() {
		String colString = " |";
		String firstDiv = " |";
		for (int c = 0; c < this.c; c++) {
			colString += " " + Integer.toString(c) + " |";
			firstDiv += "---|";
		}
		System.out.println(colString);
		System.out.println(firstDiv);
		for (int i = 0; i < this.r; i++) {
			String toCat = Integer.toString(i) + "|";
			String midDiv = " |";
			for(int j = 0; j < this.c; j++) {
				toCat += " " + this.tiles.get(i).get(j).toString() + " |";
				midDiv += "---|";
			}
			System.out.println(toCat);
			System.out.println(midDiv);
		}
	}
}
