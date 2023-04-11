public class Tile {
    protected int row;
    protected int col;
    protected boolean occupied;
    
    public Tile(int row, int col) {
        this.row = row;
        this.col = col;
        this.occupied = false;
    }
    
    public void setOccupied() {
    	this.occupied = true;
    }
    
    public void clearOccupied() {
    	this.occupied = false;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public String toString() {
    	String ret;
    	if (occupied) {
    		ret = "@";
    	}
    	else {
    		ret = " ";
    	}
    	return ret;
    }
}
