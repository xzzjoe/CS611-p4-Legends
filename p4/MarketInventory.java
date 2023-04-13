import java.util.ArrayList;

public class MarketInventory<T extends Item> extends Inventory{

    public MarketInventory(){
        this.itemList = generateGameItemList();
    }

    //read all the items from config
    public ArrayList<Item> generateGameItemList(){
        ArrayList<Item> temp = new ArrayList<>();
        //read weaponry
        temp.addAll(WeaponLoader.loadWeapons("configs/Weaponry.txt"));
        //read armory
        temp.addAll(ArmorLoader.loadArmors("configs/Armory.txt"));
        //read potion
        temp.addAll(PotionLoader.loadPotions("configs/Potions.txt"));
        //read spells
        temp.addAll(SpellLoader.loadSpells("configs/FireSpells.txt", FireSpell.class));
        temp.addAll(SpellLoader.loadSpells("configs/IceSpells.txt", IceSpell.class));
        temp.addAll(SpellLoader.loadSpells("configs/LightningSpells.txt", LightningSpell.class));
        return temp;
    }
    public void displayItems(Class<T> gameItemType){
        for (Item g : itemList) {
            if(g.getClass() == gameItemType)
                System.out.println(itemList.indexOf(g) + ": " + g);
        }
    }
}

