import java.util.ArrayList;

public class HeroInventory<T extends GameItem> extends Inventory{
    public HeroInventory() {this.gameItemList = new ArrayList<>();}


    public void printItemsByType(Class<T> gameItemType){
        for (GameItem g : gameItemList) {
            if(g.getClass() == gameItemType)
                System.out.println(gameItemList.indexOf(g) + ": " + g);
        }
    }
}
