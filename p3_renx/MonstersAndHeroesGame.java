import java.util.*;


public class MonstersAndHeroesGame extends Game {
    //TODO make this class a singleton pattern
    Scanner scanner;
    MonstersAndHerosWorld mhWorld;
    HeroTeam heroTeam;
    List<Hero> heroList;
    List<Monster> monsterList;
    public MonstersAndHeroesGame (){
        // create World
        scanner = new Scanner(System.in);
        this.mhWorld = new MonstersAndHerosWorld(8, 8);


        //read config and print out all the heroes
        heroList = new ArrayList<>();
        heroList.addAll(HeroLoader.loadHeroes(Main.SRC_FILEPATH+"\\configs\\Paladins.txt", Paladin.class));
        heroList.addAll(HeroLoader.loadHeroes(Main.SRC_FILEPATH+"\\configs\\Sorcerers.txt", Sorcerer.class));
        heroList.addAll(HeroLoader.loadHeroes(Main.SRC_FILEPATH+"\\configs\\Warriors.txt", Warrior.class));
        for (Hero h : heroList) {
            System.out.println(heroList.indexOf(h) + ": " + h);
        }
        //read config for all monsters
        monsterList = new ArrayList<>();
        monsterList.addAll(MonsterLoader.loadMonsters(Main.SRC_FILEPATH+"\\configs\\Spirits.txt", Spirit.class));
        monsterList.addAll(MonsterLoader.loadMonsters(Main.SRC_FILEPATH+"\\configs\\Dragons.txt", Dragon.class));
        monsterList.addAll(MonsterLoader.loadMonsters(Main.SRC_FILEPATH+"\\configs\\Exoskeletons.txt", Exoskeleton.class));
        //randomize the list of monsters
        Collections.shuffle(monsterList, new Random());

        //create hero team
        //ask user party size, heroes to choose
        System.out.println(Main.ANSI_GREEN+"Please enter the size of your party (1~3):"+Main.ANSI_RESET);
        int partySize = scanner.nextInt();
        List<Hero> chosenHeroes = new ArrayList<>();
        for (int i=0; i<partySize; i++){
            //TODO restrict user input between 0~sizeof(heroList)
            for (Hero h : heroList) {
                System.out.println(heroList.indexOf(h) + ": " + h);
            }
            System.out.println(Main.ANSI_GREEN+"Please choose a hero to recruit:"+Main.ANSI_RESET);
            int choice = scanner.nextInt();
            chosenHeroes.add(heroList.get(choice));
            heroList.remove(choice);
        }
        this.heroTeam = new HeroTeam(chosenHeroes);
        System.out.println(Main.ANSI_YELLOW+"You created a party of heroes! They are:\n"+heroTeam.toString()+Main.ANSI_RESET);


    }
    public void startGame (){
        char input;
        while (true) {
            //hero team starts at (0, 0)
            mhWorld.printGrid(heroTeam.getRow(), heroTeam.getCol());
            //special interactions:
            //1. Common Space, roll dice for Monster occurrence
            //   if encountered with monsters, handle battle rounds
            checkCommon();

            //2. Command M, look for Market Space, call checkMarket()
            //   if chose to enter Market, handle buy and sell
            //3. other user commands

            System.out.println(Main.ANSI_GREEN+"Move your hero using W/A/S/D; I - info; M - Market; Q - quit:"+Main.ANSI_RESET);
            //prepare values for next HeroTeam position
            int newRow = heroTeam.getRow();
            int newCol = heroTeam.getCol();

            //scan command:
            //TODO other commands: I for info
            input = scanner.next().charAt(0);
            if (input == 'W' || input == 'w') {
                newRow--;
            } else if (input == 'A' || input == 'a') {
                newCol--;
            } else if (input == 'S' || input == 's') {
                newRow++;
            } else if (input == 'D' || input == 'd') {
                newCol++;
            } else if (input == 'Q' || input == 'q') {
                endGame();
            } else if (input == 'I' || input == 'i') {
                showInfo();
                continue;
            } else if (input == 'M' || input == 'm') {
                checkMarket();
                continue;
            } else {
                System.out.println("Invalid command input! Use W/A/S/D keys to move, or enter Q to quit");
                continue;
            }

            if (mhWorld.isValidMove(newRow, newCol)) {
                heroTeam.moveTo(newRow, newCol);
                heroTeam.increaseSpaceTravelled();
            } else {
                System.out.println("Invalid move, please try again");
            }
        }
    }
    public void endGame(){
        System.out.println("-----------------------------------\n" +
                "Number of Monsters defeated:" + heroTeam.getMonsterDefated() +
                "\nNumber of Spaces travelled: "+ heroTeam.getSpaceTravelled()+
                "\nGame end");
        System.exit(0);
    }
    //TODO team info, use items/equipments
    public void showInfo() {
        System.out.println("==================================================\nHero Team info:\n" +
                heroTeam.toString() +
                "==================================================");
        System.out.println(Main.ANSI_GREEN+"Do you want to use items(Equipments or Potions)? Choose a Hero index to access their inventory, or -1 to go back\n"+Main.ANSI_RESET);
        char input = scanner.next().charAt(0);
        int heroIndex;
        try {
            heroIndex = Integer.parseInt(String.valueOf(input));
        } catch (Exception e) {
            System.out.println("Input format is wrong, please try again:");
            return;
        }
        Hero h = heroTeam.getParty().get(heroIndex);
        System.out.println("Choose an item (enter an index, or -1 to go back): \n" + h.printAllItems());
        int itemIndex = scanner.nextInt();
        if (itemIndex >= 0) {
            GameItem e = h.getFromInventory(itemIndex);
            if (e instanceof Weapon) {
                h.setWeapon((Weapon) e);
            } else if (e instanceof Armor) {
                h.setArmor((Armor) e);
            } else if (e instanceof Potion) {
                //choose a target to use potion
                System.out.println("Choose a target (enter index): \n"+heroTeam.toString());
                int targetIndex = scanner.nextInt();
                Hero potionTarget = heroTeam.getParty().get(targetIndex);
                //perform move
                h.usePotion((Potion)e, potionTarget);
            } else {
                //no spell
                System.out.println("Input is wrong, please try again");
            }
        }
    }
    public void checkMarket(){
        mhWorld.checkMarket(heroTeam);
    }

    public void checkCommon() {
        //check if this is a Common Space
        if(mhWorld.checkCommon(heroTeam)) {
            Random random = new Random();
            //roll a die for encounter
            if (random.nextInt(100) < 30) { // 30% chance
                System.out.println("==================================================\nEncountered a monster team");
                List<Monster> tempMonsterTeam = new ArrayList<>();
                while (tempMonsterTeam.size() != heroTeam.getParty().size()) {
                    Monster m = monsterList.get(0);
                    monsterList.remove(m);
                    tempMonsterTeam.add(m);
                }
                MonsterTeam monsterTeam = new MonsterTeam(tempMonsterTeam);
                Battle battle = new Battle(heroTeam, monsterTeam);
                //start battle
                battle.startBattle();
                //logic after battle
                battle.finishBattle();
                //if lost: print defeated prompt, endGame()
                if (!heroTeam.isAlive()) {
                    System.out.println(Main.ANSI_RED+"All heroes fainted."+Main.ANSI_RESET);
                    endGame();
                } else
                    //battle won, continue
                    mhWorld.printGrid(heroTeam.getRow(), heroTeam.getCol());
            } else
                System.out.println("==================================================\nNothing happened");
        }

    }
}
