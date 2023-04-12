public class MaketInventory extends Inventory{

    public MarketInventory(){
        this.itemList = generateGameItemList();
    }

    //read all the items from config
    public List<GameItem> generateGameItemList(){
        List<GameItem> temp = new ArrayList<>();
        //read weaponry
        temp.addAll(WeaponLoader.loadWeapons(Main.SRC_FILEPATH+"\\configs\\Weaponry.txt"));
        //read armory
        temp.addAll(ArmorLoader.loadArmors(Main.SRC_FILEPATH+"\\configs\\Armory.txt"));
        //read potion
        temp.addAll(PotionLoader.loadPotions(Main.SRC_FILEPATH+"\\configs\\Potions.txt"));
        //read spells
        temp.addAll(SpellLoader.loadSpells(Main.SRC_FILEPATH+"\\configs\\FireSpells.txt", FireSpell.class));
        temp.addAll(SpellLoader.loadSpells(Main.SRC_FILEPATH+"\\configs\\IceSpells.txt", IceSpell.class));
        temp.addAll(SpellLoader.loadSpells(Main.SRC_FILEPATH+"\\configs\\LightningSpells.txt", LightningSpell.class));
        return temp;
    }
    public void displayItems(Class<T> gameItemType){
        for (GameItem g : gameItemList) {
            if(g.getClass() == gameItemType)
                System.out.println(gameItemList.indexOf(g) + ": " + g);
        }
    }
}
}
