import java.util.*;

public class LegendsOfValorGame extends Game {


    //TODO make this class a singleton pattern
    Scanner scanner;
    LegendsOfValorWorld lvWorld;
    HeroTeam heroTeam;
    List<Hero> heroList;
    List<Monster> monsterList;
    MonsterTeam monsterTeam;
    private int roundCounter;

    public LegendsOfValorGame (){
        // create World
        scanner = new Scanner(System.in);
        this.lvWorld = new LegendsOfValorWorld(8, 8);
        //read config and print out all the heroes
        heroList = new ArrayList<>();
        heroList.addAll(HeroLoader.loadHeroes("configs\\Paladins.txt", Paladin.class));
        heroList.addAll(HeroLoader.loadHeroes("configs\\Sorcerers.txt", Sorcerer.class));
        heroList.addAll(HeroLoader.loadHeroes("configs\\Warriors.txt", Warrior.class));
        for (Hero h : heroList) {
            System.out.println(heroList.indexOf(h) + ": " + h);
        }
        readMonster();
        this.monsterTeam = new MonsterTeam(new ArrayList<>());
        spawnMonster(3);

        //create hero team
//        //ask user party size, heroes to choose
//        System.out.println(Main.ANSI_GREEN+"Please enter the size of your party (1~3):"+Main.ANSI_RESET);
//        int partySize = scanner.nextInt();
        int partySize = 3;
        List<Hero> chosenHeroes = new ArrayList<>();
        for (int i=0; i<partySize; i++){

            for (Hero h : heroList) {
                System.out.println(heroList.indexOf(h) + ": " + h);
            }
            //Input validation: restrict user input between 0~sizeof(heroList)
            int choice = -1;
            do {
                System.out.println(Main.ANSI_GREEN+"Your party size is "+partySize+". Please choose a hero to recruit:"+Main.ANSI_RESET);
                System.out.printf("Enter a number between %d and %d: ", 0, this.heroList.size()-1);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, this.heroList.size()-1);
                }
                choice = scanner.nextInt();
            } while (choice < 0 || choice > this.heroList.size()-1);
            chosenHeroes.add(heroList.get(choice));
            heroList.remove(choice);
            //initialize hero starting position
            switch (i){
                //top lane hero
                case 0:
                    chosenHeroes.get(0).setStartingLane('t');
                    chosenHeroes.get(0).respawn();
                    //move:
                    break;
                //mid lane hero
                case 1:
                    chosenHeroes.get(0).setStartingLane('m');
                    chosenHeroes.get(0).respawn();
                    break;
                //bottom lane hero
                case 2:
                    chosenHeroes.get(0).setStartingLane('b');
                    chosenHeroes.get(0).respawn();
                    break;
            }
        }
        this.heroTeam = new HeroTeam(chosenHeroes);

        System.out.println(Main.ANSI_YELLOW+"You created a party of heroes! They are:\n"+heroTeam.toString()+Main.ANSI_RESET);

    }
    public void startGame (){
        char input;
        roundCounter = 0;
        while (true) {
            //heroes start at their starting positions (0, 7), (3, 7), (6, 7)
            //monsters start at their starting positions (1, 0), (4, 0), (7, 0)
            lvWorld.printBoard();
            //todo redesign main game loop
            //todo test case for marketInventory
            MarketInventory testM = new MarketInventory<>();
            testM.displayItems(Potion.class);
            System.out.println("/************************************************/");
            System.out.println("This is round "+roundCounter+1);
            //1. loop through HeroTeam, call heroTurn()
            System.out.println("/*******************Heroes' Turn*****************/");
            for(Hero h: heroTeam.getParty()){
                System.out.println("/************************************************/");
                if (h.isAlive())
                    heroTurn(h);
                else{
                    System.out.println(Main.ANSI_RED +"It's " + h.getName() + "'s turn, but " +h.getName()+ " is fainted and waiting to be revived"+Main.ANSI_RESET);
//                    todo h.reviveAtNexus();
                    //stop displaying hero when it's fainted
                    //delay one round and then revive
                }
                //after a move, check win condition
                if (reachNexus()) {
                    endGame();
                }
            }
            //2. loop through MonsterTeam, call monsterTurn()
            System.out.println("/*****************Monsters' Turn*****************/");
            for(Monster m: monsterTeam.getParty()){
                System.out.println("/************************************************/");
                if (m.isAlive())
                    monsterTurn(m);
                else
                    System.out.println(Main.ANSI_RED +"It's " + m.getName() + "'s turn, but " +m.getName()+ " is defeated and cannot move!"+Main.ANSI_RESET);

                //after a move, check win condition
                if (reachNexus()) {
                    endGame();
                }
            }
            //restore hp&mp after each round
            heroTeam.restoreHpAndMana();

            //check for spawning new monsters
            if(roundCounter%8==0)
                spawnMonster(3);
            roundCounter++;

            //todo move the following interaction when moving to a Space, to a round manager class
            /*
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
//                showInfo();
                continue;
            } else if (input == 'M' || input == 'm') {
                checkMarket();
                continue;
            } else {
                System.out.println("Invalid command input! Use W/A/S/D keys to move, or enter Q to quit");
                continue;
            }

            if (lvWorld.isValidMove(newRow, newCol)) {
                heroTeam.moveTo(newRow, newCol);
                heroTeam.increaseSpaceTravelled();
            } else {
                System.out.println("Invalid move, please try again");
            }

             */
        }
    }

    private void heroTurn(Hero h) {
        System.out.println("Hero "+h.getName()+"'s turn, to be implemented");

        boolean turnTaken = false;
        while (!turnTaken) {
            System.out.println(Main.ANSI_GREEN + "It's " + h.getName() + "'s turn.\n" +
                    "Please choose an action: \n" +
                    "0. Move\n" +
                    "1. Attack\n" +
                    "2. Cast spell\n" +
                    "3. Use potion\n" +
                    "4. Equip item\n" +
                    "5. Display stats (won't consume this turn)\n" + Main.ANSI_RESET);
            scanner.next();

            turnTaken = true;
        }
    }

    private void monsterTurn(Monster m) {
        System.out.println("Monster "+m.getName()+"'s turn, to be implemented");
    }

    //a function to
    private boolean reachNexus(){
        for(Monster m: this.monsterTeam.getParty()){
            if (m.getR()==0)
                return true;
        }
        for(Hero h:this.heroTeam.getParty()){
            if (h.getR()==7)
                return true;
        }
        return false;
    }
    public void endGame(){
        //decide who won
        boolean heroWon = true;
        for(Monster m: this.monsterTeam.getParty()){
            if (m.getR()==7)
                heroWon = false;
        }
        if(heroWon){
            System.out.println("Hero reaches monsters' Nexus");
        }
        else{
            System.out.println("Monster reaches heroes' Nexus");
        }
        System.out.println("-----------------------------------" +
//                "\nNumber of Monsters defeated:" + heroTeam.getMonsterDefated() +
//                "\nNumber of Spaces travelled: "+ heroTeam.getSpaceTravelled()+
                "\nGame end");
        System.exit(0);
    }

    private void spawnMonster(int num){
        for(int i=0; i<num; i++){
            if(this.monsterList.size()==0)
                //read a new monsterList when the previous one became empty
                readMonster();
            Monster chosen = this.monsterList.get(0);
            this.monsterTeam.getParty().add(chosen);
            //initialize monster starting position
            switch (i){
                //top lane
                case 0:
                    //
                    break;
                //mid lane
                case 1:
                    //
                    break;
                //bottom lane
                case 2:
                    break;
            }
            this.monsterList.remove(0);

        }
    }
    private void readMonster(){
        //read config for all monsters
        monsterList = new ArrayList<>();
        monsterList.addAll(MonsterLoader.loadMonsters("configs\\Spirits.txt", Spirit.class));
        monsterList.addAll(MonsterLoader.loadMonsters("configs\\Dragons.txt", Dragon.class));
        monsterList.addAll(MonsterLoader.loadMonsters("configs\\Exoskeletons.txt", Exoskeleton.class));
        //monsterList.addAll(MonsterLoader.loadMonsters(Main.SRC_FILEPATH+"\\configs\\Exoskeletons.txt", Exoskeleton.class));
        //randomize the list of monsters
        Collections.shuffle(monsterList, new Random());
    }

//    //TODO team info, use items/equipments
//    public void showInfo() {
//        System.out.println("==================================================\nHero Team info:\n" +
//                heroTeam.toString() +
//                "==================================================");
//        System.out.println(Main.ANSI_GREEN+"Do you want to use items(Equipments or Potions)? Choose a Hero index to access their inventory, or -1 to go back\n"+Main.ANSI_RESET);
//        char input = scanner.next().charAt(0);
//        int heroIndex;
//        try {
//            heroIndex = Integer.parseInt(String.valueOf(input));
//        } catch (Exception e) {
//            System.out.println("Input format is wrong, please try again:");
//            return;
//        }
//        Hero h = heroTeam.getParty().get(heroIndex);
//        System.out.println("Choose an item (enter an index, or -1 to go back): \n" + h.printAllItems());
//        int itemIndex = scanner.nextInt();
//        if (itemIndex >= 0) {
//            GameItem e = h.getFromInventory(itemIndex);
//            if (e instanceof Weapon) {
//                h.setWeapon((Weapon) e);
//            } else if (e instanceof Armor) {
//                h.setArmor((Armor) e);
//            } else if (e instanceof Potion) {
//                //choose a target to use potion
//                System.out.println("Choose a target (enter index): \n"+heroTeam.toString());
//                int targetIndex = scanner.nextInt();
//                Hero potionTarget = heroTeam.getParty().get(targetIndex);
//                //perform move
//                h.usePotion((Potion)e, potionTarget);
//            } else {
//                //no spell
//                System.out.println("Input is wrong, please try again");
//            }
//        }
//    }
//
//    public void checkMarket(){
//        lvWorld.checkMarket(heroTeam);
//    }
//
//    public void checkCommon() {
//        //check if this is a Common Space
//        if(lvWorld.checkCommon(heroTeam)) {
//            Random random = new Random();
//            //roll a die for encounter
//            if (random.nextInt(100) < 30) { // 30% chance
//                System.out.println("==================================================\nEncountered a monster team");
//                List<Monster> tempMonsterTeam = new ArrayList<>();
//                while (tempMonsterTeam.size() != heroTeam.getParty().size()) {
//                    Monster m = monsterList.get(0);
//                    monsterList.remove(m);
//                    tempMonsterTeam.add(m);
//                }
//                MonsterTeam monsterTeam = new MonsterTeam(tempMonsterTeam);
//                Battle battle = new Battle(heroTeam, monsterTeam);
//                //start battle
//                battle.startBattle();
//                //logic after battle
//                battle.finishBattle();
//                //if lost: print defeated prompt, endGame()
//                if (!heroTeam.isAlive()) {
//                    System.out.println(Main.ANSI_RED+"All heroes fainted."+Main.ANSI_RESET);
//                    endGame();
//                } else
//                    //battle won, continue
//                    lvWorld.printBoard();
//            } else
//                System.out.println("==================================================\nNothing happened");
//        }
//
//    }
}

