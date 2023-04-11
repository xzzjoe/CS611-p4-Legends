import java.util.List;

public class MarketTile extends Tile {
	private Market m;
	public MarketTile(int r, int j, List<Item> market) {
		super(r, j);
		this.m = new Market(market);
	}
	
	public String toString() {
		if (this.occupied) {
			return "@";
		}
		String ret = "$";
		return ret;
	}
	
	public Market getMarket() {
		return this.m;
	}
	
}
