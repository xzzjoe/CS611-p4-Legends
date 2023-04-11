public class MonsterTile extends Tile{
    private boolean isOccupied;
    private Monster occupyingMonster;
    
    public MonsterTile(int row, int col) {
        super(row, col);
        this.isOccupied = false;
        this.occupyingMonster = null;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public boolean isOccupied() {
        return isOccupied;
    }
    
    public Monster getMonster() {
        return occupyingMonster;
    }
    
    public void setMonster(Monster monster) {
        this.occupyingMonster = monster;
        this.isOccupied = true;
    }
    
    public void clearMonster() {
        this.occupyingMonster = null;
        this.isOccupied = false;
    }
    
    public String toString() {
    	if (this.occupied) {
    		if (this.isOccupied) {
    			return "B";
    		}
    		return "@";
    	}
    	if (this.isOccupied) return "M";
    	return " ";
    }
}