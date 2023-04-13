import java.util.Scanner;

//Ancient & Market
public class Nexus extends Space{

    private Scanner scanner;
    private MarketInventory marketInventory;
    public Nexus(){
        super();
        this.mark = 'N';
        this.marketInventory = new MarketInventory<>();
        this.scanner = new Scanner(System.in);
    }

    public void tradeWithHero(Hero hero){
        //1. print hero info + hero inventory, use trade() to ask buy or sell by checking int input
        //2.1 buy:ask type of item to buy
        //2.2 show marketInventory of selected type, ask for index to buy, or
        //2.3 check condition and deal, move item
        //2.4 return to step 0.

        //3.1 sell:show inventory of hero, ask for index to sell
        //3.2 deal, move item
        //3.3 return to step 0.
        System.out.println("-------------------------------\n" +Main.ANSI_GREEN+
                "Welcome to the market in Neuxs!"+Main.ANSI_RESET);
        int choice;
        while(true){
            System.out.println("=================================\nStart trading with: ");
            System.out.println("Hero Info: " + hero.toString());
            //TODO decorator class for hero inventory
//            System.out.println("Hero Inventory: " + hero.getInventory().toString());

            System.out.println("Choose an action:");
            System.out.println("1. Buy");
            System.out.println("2. Sell");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            // Buy
            if (choice == 1) {
                System.out.println("-------------------------------\n" + Main.ANSI_GREEN+
                        "To Buy: Choose the type of item you want to check(1-4), or enter 0 to leave:\n" +
                        "1. Weaponry\n" +
                        "2. Armory\n" +
                        "3. Potions\n" +
                        "4. Spells\n" +
                        "0. leave"+Main.ANSI_RESET);
                int input = scanner.nextInt();
                //weapon
                if (input == 1) {
                    marketInventory.displayItems(Weapon.class);
                    //ask for item index
                    System.out.println(Main.ANSI_GREEN+"Enter the index of the item to buy, or -1 to go back:"+Main.ANSI_RESET);
                    input = scanner.nextInt();
                    //check illegal inputs
                    if (input >= 0 && input <= marketInventory.itemList.size() && marketInventory.itemList.get(input).getClass()==Weapon.class) {
                        if (sellItemToHero(input, hero)) {
                            System.out.println(Main.ANSI_YELLOW+"Purchase successful."+Main.ANSI_RESET);
                        } else {
                            System.out.println("Purchase failed. Check conditions and try again.");
                        }
                    }else{
                        System.out.println("Purchase failed. Check conditions and try again.");
                    }
                    //armor
                } else if (input == 2) {
                    marketInventory.displayItems(Armor.class);
                    //ask for item index
                    System.out.println(Main.ANSI_GREEN+"Enter the index of the item to buy, or -1 to go back:"+Main.ANSI_RESET);
                    input = scanner.nextInt();
                    //check illegal inputs
                    if (input >= 0 && input <= marketInventory.itemList.size() && marketInventory.itemList.get(input).getClass()==Armor.class) {
                        if (sellItemToHero(input, hero)) {
                            System.out.println(Main.ANSI_YELLOW+"Purchase successful."+Main.ANSI_RESET);
                        } else {
                            System.out.println("Purchase failed. Check conditions and try again.");
                        }
                    }else{
                        System.out.println("Purchase failed. Check conditions and try again.");
                    }
                    //Potion
                } else if (input == 3) {
                    marketInventory.displayItems(Potion.class);
                    //ask for item index
                    System.out.println(Main.ANSI_GREEN+"Enter the index of the item to buy, or -1 to go back:"+Main.ANSI_RESET);
                    input = scanner.nextInt();
                    //check illegal inputs
                    if (input >= 0 && input <= marketInventory.itemList.size() && marketInventory.itemList.get(input).getClass()==Potion.class) {
                        if (sellItemToHero(input, hero)) {
                            System.out.println(Main.ANSI_YELLOW+"Purchase successful."+Main.ANSI_RESET);
                        } else {
                            System.out.println("Purchase failed. Check conditions and try again.");
                        }
                    }else{
                        System.out.println("Purchase failed. Check conditions and try again.");
                    }
                    //Spells
                } else if (input == 4) {
                    marketInventory.displayItems(IceSpell.class);
                    marketInventory.displayItems(FireSpell.class);
                    marketInventory.displayItems(LightningSpell.class);
                    //ask for item index
                    System.out.println(Main.ANSI_GREEN+"Enter the index of the item to buy, or -1 to go back:"+Main.ANSI_RESET);
                    input = scanner.nextInt();
                    //check illegal inputs
                    if (input >= 0 && input <= marketInventory.itemList.size() && marketInventory.itemList.get(input) instanceof Spell) {
                        if (sellItemToHero(input, hero)) {
                            System.out.println(Main.ANSI_YELLOW+"Purchase successful."+Main.ANSI_RESET);
                        } else {
                            System.out.println("Purchase failed. Check conditions and try again.");
                        }
                    }else{
                        System.out.println("Purchase failed. Check conditions and try again.");
                    }
                } else if (input == 0) {
                    return;
                } else {
                    System.out.println("Invalid input! Use 1-4 to choose, or enter 0 to leave");
                }
                // TODO Sell
            } else if (choice == 2) {
                System.out.println("Hero Inventory: \n" + hero.printAllItems());
                System.out.println(Main.ANSI_GREEN+"Enter the index of the item to sell, or -1 to go back:"+Main.ANSI_RESET);
                int itemIndex = scanner.nextInt();

                if (itemIndex >= 0) {
                    buyItemFromHero(itemIndex, hero);
                    System.out.println(Main.ANSI_YELLOW+"Item sold."+Main.ANSI_RESET);
                }
                // Exit
            } else if (choice == 3) {
                System.out.println("-------------------------------\n" +hero.getName()+
                        " left the market");
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public boolean sellItemToHero(int itemID, Hero buyer){
        //check money, pay money
        Item i = marketInventory.itemList.get(itemID);
        if (buyer.getMoney() < i.purchasePrice()){
            System.out.println("Not enough Money!");
            return false;
        }
        else {
            buyer.setMoney(buyer.getMoney() - i.purchasePrice());
            buyer.addToInventory(i);
            marketInventory.itemList.remove(itemID);
            return true;
        }
    }

    public void buyItemFromHero(int itemID, Hero seller){
        Item received = seller.removeFromInventory(itemID);
        seller.setMoney(seller.getMoney() + received.sellPrice());
        marketInventory.itemList.add(received);
    }
}
