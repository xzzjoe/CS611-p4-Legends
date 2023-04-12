import java.util.ArrayList;

public class HeroInventory<T extends Item> extends Inventory{
    public HeroInventory() {this.itemList = new ArrayList<>();}

    public void printItemsByType(Class<T> gameItemType){
        for (Item g : itemList) {
            if(g.getClass() == gameItemType)
                System.out.println(itemList.indexOf(g) + ": " + g);
        }
    }
}
