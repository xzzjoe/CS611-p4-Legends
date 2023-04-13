import java.util.*;

public class LegendsOfValorGame extends Game {


    //TODO make this class a singleton pattern
    private Scanner scanner;
    private LegendsOfValorWorld lvWorld;
    private HeroTeam heroTeam;
    private List<Hero> heroList;
    private List<Monster> monsterList;
    private MonsterTeam monsterTeam;

    public LegendsOfValorGame (){
        // create World
        scanner = new Scanner(System.in);
        this.lvWorld = new LegendsOfValorWorld(8, 8);
        //read config and print out all the heroes
        heroList = new ArrayList<>();
        heroList.addAll(HeroLoader.loadHeroes("configs\\Paladins.txt", Paladin.class));
        heroList.addAll(HeroLoader.loadHeroes("configs\\Sorcerers.txt", Sorcerer.class));
        heroList.addAll(HeroLoader.loadHeroes("configs\\Warriors.txt", Warrior.class));
        //create hero team
//        //ask user party size, heroes to choose
//        System.out.println(Main.ANSI_GREEN+"Please enter the size of your party (1~3):"+Main.ANSI_RESET);
//        int partySize = scanner.nextInt();
        int partySize = 3;
        List<Hero> chosenHeroes = new ArrayList<>();
        for (int i=0; i<partySize; i++){
            for (Hero h : heroList) {
                System.out.println(heroList.indexOf(h) + ": " + h.toString());
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
            Hero chosen = heroList.get(choice);
            //initialize hero starting position
            switch (i){
                //top lane hero
                case 0:
                    chosen.setStartingLane('t');
                    chosen.respawn();
                    lvWorld.board[7][0].addHero(chosen);
                    //move:
                    break;
                //mid lane hero
                case 1:
                    chosen.setStartingLane('m');
                    chosen.respawn();
                    lvWorld.board[7][3].addHero(chosen);
                    break;
                //bottom lane hero
                case 2:
                    chosen.setStartingLane('b');
                    chosen.respawn();
                    lvWorld.board[7][6].addHero(chosen);
                    break;
            }
            heroList.remove(chosen);
        }
        this.heroTeam = new HeroTeam(chosenHeroes);

        System.out.println(Main.ANSI_YELLOW+"You created a party of heroes! They are:\n"+heroTeam+Main.ANSI_RESET);

        readMonster();
        this.monsterTeam = new MonsterTeam(new ArrayList<>());
        spawnMonster(3, heroTeam.getAverageLevel());

    }
    public void startGame (){
        char input;
        int roundCounter = 1;
        while (true) {
            //heroes start at their starting positions (0, 7), (3, 7), (6, 7)
            //monsters start at their starting positions (1, 0), (4, 0), (7, 0)
            lvWorld.printBoard();
            System.out.println("/************************************************/");
            System.out.println("This is round "+ roundCounter);
            //1. loop through HeroTeam, call heroTurn()
            System.out.println("/*******************Heroes' Turn*****************/");
            for(Hero h: heroTeam.getParty()){
                System.out.println("/************************************************/");
                if (h.isAlive()) {
                    heroTurn(h);
                    lvWorld.printBoard();
                }
                else if(h.revive()){
                    h.respawn();
                    lvWorld.board[h.getR()][h.getC()].addHero(h);
                    lvWorld.printBoard();
                    heroTurn(h);
                    lvWorld.printBoard();
                }
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
                if (m.isAlive()){
                    monsterTurn(m);
                    lvWorld.printBoard();
                }
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
            if(roundCounter %8==0)
                spawnMonster(3, heroTeam.getAverageLevel());
            roundCounter++;
        }
    }

    private void heroTurn(Hero h) {
        System.out.println("Hero "+h.getName()+"'s turn, to be implemented");

        boolean turnTaken = false;
        //if this hero is reviving, consume this turn
        //todo add prompt inside revive(), and
        while (!turnTaken) {
            System.out.println(Main.ANSI_GREEN + "It's " + h.getName() + "'s turn.\n" +
                    "Please choose an action: \n" +
                    "0. Move\n" +
                    "1. Attack\n" +
                    "2. Change equipment\n" +
                    "3. Use potion\n" +
                    "4. Cast spell\n" +
                    "5. Display stats\n" +
                    "6. Teleport to another Lane\n" +
                    "7. Recall to Nexus\n" +
                    "8. Buy/Sell at Nexus\n" +
                    "9. Quit Game\n" + Main.ANSI_RESET);
            //scan next int input 0~7
            int choice = -1;
            do {
                System.out.printf("Enter a number between %d and %d: ", 0, 9);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, 9);
                }
                choice = scanner.nextInt();
            } while (choice < 0 || choice > 9);
            switch (choice){
                //move
                case 0:
                    turnTaken = heroMovePosition(h);
                    break;
                //attack
                case 1:
                    turnTaken = heroAttack(h);
                    break;
                //equip
                case 2:
                    turnTaken = heroEquip(h);
                    break;
                //potion
                case 3:
                    turnTaken = heroUsePotion(h);
                    break;
                //spell
                case 4:
                    turnTaken = heroUseSpell(h);
                    break;
                //info
                case 5:
                    showInfo();
                    break;
                //teleport
                case 6:
                    turnTaken = heroTeleport(h);
                    break;
                //recall
                case 7:
                    turnTaken = heroRecall(h);
                    break;
                //market nexus
                case 8:
                    if(lvWorld.checkMarket(h)){
                        Nexus n = (Nexus) lvWorld.board[h.getR()][h.getC()];
                        n.tradeWithHero(h);
                    }
                    break;
                //quit game
                case 9:
                    System.out.println("Quiting game......");
                    endGame();
                    break;
            }
        }
    }

    private void monsterTurn(Monster m) {
        //search inRange()
        ArrayList<Fightable> targetList = lvWorld.inRange(m);
        if(targetList.size()==0){
            //no hero in range, move forward
            if(lvWorld.isValidMove((m.getR()+1), (m.getC()), m)){
                lvWorld.board[m.getR()][m.getC()].removeMonster();
                m.makeMove((m.getR()+1), (m.getC()));
                lvWorld.board[m.getR()][m.getC()].addMonster(m);
            }
            //if forward space's isValid = false, check isValid for left and right space
            else if(lvWorld.isValidMove((m.getR()), (m.getC()+1), m)) {
                lvWorld.board[m.getR()][m.getC()].removeMonster();
                m.makeMove((m.getR()), (m.getC()+1));
                lvWorld.board[m.getR()][m.getC()].addMonster(m);
            }
            else if(lvWorld.isValidMove((m.getR()), (m.getC()-1), m)) {
                lvWorld.board[m.getR()][m.getC()].removeMonster();
                m.makeMove((m.getR()), (m.getC()-1));
                lvWorld.board[m.getR()][m.getC()].addMonster(m);
            }
            else{
                //no place to move
            }
        }
        else{
            //auto-choose the first hero in range to attack
            Hero h = (Hero) targetList.get(0);
            m.attack(h);
            if(!h.isAlive()){
                lvWorld.board[h.getR()][h.getC()].removeHero();
            }
        }
    }

    private boolean heroMovePosition(Hero h){
        while(true) {
            System.out.println(Main.ANSI_GREEN + "Move your hero using W/A/S/D" + Main.ANSI_RESET);
            //prepare values for next HeroTeam position
            int newRow = h.getR();
            int newCol = h.getC();

            //scan command:
            char input = scanner.next().charAt(0);
            if (input == 'W' || input == 'w') {
                newRow--;
            } else if (input == 'A' || input == 'a') {
                newCol--;
            } else if (input == 'S' || input == 's') {
                newRow++;
            } else if (input == 'D' || input == 'd') {
                newCol++;
            } else {
                System.out.println("Invalid command input! Use W/A/S/D keys to move");
                continue;
            }

            if (lvWorld.isValidMove(newRow, newCol, h)) {
                lvWorld.board[h.getR()][h.getC()].removeHero();
                h.makeMove(newRow, newCol);
                lvWorld.board[h.getR()][h.getC()].addHero(h);
                return true;
            } else {
                System.out.println("Invalid move, please try again");
                return false;
            }
        }
    }

    private boolean heroAttack(Hero h){
        ArrayList<Fightable> targetList = lvWorld.inRange(h);
        if(targetList.size()==0){
            System.out.println("There is no target in range");
            return false;
        }
        else{
            for(Fightable f :targetList){
                Monster m = (Monster) f;
                System.out.println(targetList.indexOf(m)+": "+ m);
            }
            System.out.println("Choose a target to attack:");
            //scan next int input 0~targetList size
            int choice = -1;
            do {
                System.out.printf("Enter a number between %d and %d: ", 0, targetList.size()-1);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, targetList.size()-1);
                }
                choice = scanner.nextInt();
            } while (choice < 0 || choice > targetList.size()-1);
            //retrieve the target monster
            Monster m = (Monster) targetList.get(choice);
            h.attack(m);
            if(!m.isAlive()){
                lvWorld.board[m.getR()][m.getC()].removeMonster();
                monsterTeam.getParty().remove(m);
                //if win: earn money + exp and level up
                System.out.println(Main.ANSI_YELLOW+"Monster "+m.getName()+" is defeated"+Main.ANSI_RESET);
                //awards: money, exp, give to every hero
                for(Hero h1: heroTeam.getParty()){
                    int moneyEarned = m.getLevel()*100;
                    int expGained = m.getLevel()*2;
                    System.out.println(Main.ANSI_YELLOW+ h1.getName()+ " earn money: "+moneyEarned+", gain exp: "+expGained+Main.ANSI_RESET);
                    h1.setMoney(h.getMoney()+moneyEarned);
                    h1.setExperience(h.getExperience()+expGained);
                    h1.levelUp();
                }
                System.out.println("==================================================");
            }
            return true;
        }

    }

    private boolean heroEquip(Hero h){
        //ask equip or unequip
        System.out.println(Main.ANSI_GREEN+"Choose equip or unequip an item: (0-equip, 1-unequip)\n"+Main.ANSI_RESET);
        int next = scanner.nextInt();
        int choice = -1;
        //equip
        if(next==0) {
            System.out.println(Main.ANSI_GREEN + "Choose an equippable item: \n" + Main.ANSI_RESET);
            List<Equipabble> tempList = new ArrayList<>();
            for(Item i: h.getHeroInventory().itemList){
                if(i instanceof Equipabble){
                    //only show unequipped items
                    if(!((Equipabble) i).checkEquipped()) {
                        tempList.add((Equipabble) i);
                        System.out.println(h.getHeroInventory().itemList.indexOf(i) + ": " + i.getName());
                    }
                }
            }
            //if nothing is Equipabble & unequipped
            if(tempList.size()==0){
                System.out.println("Nothing in inventory is available to be equipped to "+h.getName()+", please try again");
                return false;
            }
            do {
                System.out.printf("Enter a number between %d and %d: ", 0, tempList.size()-1);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, tempList.size()-1);
                }
                choice = scanner.nextInt();
            } while (choice < 0 || choice > tempList.size()-1);
            if (choice >= 0) {
                Equipabble target =(Equipabble) h.getFromInventory(choice);
                return target.equip(h);
            }
            else
                return false;
        }
        //else unequip
        else{
            System.out.println("Choose an equipment to unequip by index:");
            //print the list of equipped
            List<Equipabble> tempList = new ArrayList<>();
            for(Item i: h.getHeroInventory().itemList){
                if(i instanceof Equipabble){
                    if(((Equipabble) i).checkEquipped()) {
                        tempList.add((Equipabble) i);
                        System.out.println(h.getHeroInventory().itemList.indexOf(i) + ": " + i.getName());
                    }
                }
            }
            //if nothing is equipped
            if(tempList.size()==0){
                System.out.println("Nothing is equipped to "+h.getName()+", please try again");
                return false;
            }
            do {
                System.out.printf("Enter a number between %d and %d: ", 0, tempList.size()-1);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, tempList.size()-1);
                }
                choice = scanner.nextInt();
            } while (choice < 0 || choice > tempList.size()-1);
            //check input and get equipment within index of heroInventory
            choice = scanner.nextInt();
            Equipabble target =(Equipabble) h.getFromInventory(choice);
            target.unequip(h);
            return true;

        }
    }

    private boolean heroUsePotion(Hero h){
        int choice = -1;
        //choose potion
        System.out.println(Main.ANSI_GREEN + "Choose a potion item: \n" + Main.ANSI_RESET);
        List<Potion> tempList = new ArrayList<>();
        for(Item i: h.getHeroInventory().itemList){
            if(i instanceof Potion){
                tempList.add((Potion) i);
                System.out.println(h.getHeroInventory().itemList.indexOf(i) + ": " + i.getName());
            }
        }
        //if no potion
        if(tempList.size()==0){
            System.out.println("Nothing in inventory is available to use, please try again");
            return false;
        }
        //input validation, get a choice value from user
        do {
            System.out.printf("Enter a number between %d and %d: ", 0, tempList.size()-1);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, tempList.size()-1);
            }
            choice = scanner.nextInt();
        } while (choice < 0 || choice > tempList.size()-1);

        Potion p = (Potion) h.getFromInventory(choice);
        return h.usePotion(p);
    }

    private boolean heroUseSpell(Hero h){
        int choice = -1;
        //choose spell
        System.out.println(Main.ANSI_GREEN + "Choose a Spell item: \n" + Main.ANSI_RESET);
        List<Spell> tempList = new ArrayList<>();
        for(Item i: h.getHeroInventory().itemList){
            if(i instanceof Spell){
                tempList.add((Spell) i);
                System.out.println(h.getHeroInventory().itemList.indexOf(i) + ": " + i.getName());
            }
        }
        //if no Spell in inventory
        if(tempList.size()==0){
            System.out.println("Nothing in inventory is available to use, please try again");
            return false;
        }
        do {
            System.out.printf("Enter a number between %d and %d: ", 0, tempList.size()-1);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, tempList.size()-1);
            }
            choice = scanner.nextInt();
        } while (choice < 0 || choice > tempList.size()-1);
        //retrieve spell
        Spell s = (Spell) h.getFromInventory(choice);

        //choose target, basically reused heroAttack() logic
        ArrayList<Fightable> targetList = lvWorld.inRange(h);
        if(targetList.size()==0){
            System.out.println("There is no target in range");
            return false;
        }
        else {
            for (Fightable f : targetList) {
                Monster m = (Monster) f;
                System.out.println(targetList.indexOf(m) + ": " + m);
            }
            System.out.println("Choose a target to cast spell on:");
            //scan next int input 0~targetList size
            do {
                System.out.printf("Enter a number between %d and %d: ", 0, targetList.size() - 1);
                while (!scanner.hasNextInt()) {
                    String input = scanner.next();
                    System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, targetList.size() - 1);
                }
                choice = scanner.nextInt();
            } while (choice < 0 || choice > targetList.size() - 1);
            //retrieve the target monster
            Monster m = (Monster) targetList.get(choice);
            if (h.castSpell(s, m)) {
                //cast success, check defeat monster or not
                if (!m.isAlive()) {
                    lvWorld.board[m.getR()][m.getC()].removeMonster();
                    monsterTeam.getParty().remove(m);
                    //if win: earn money + exp and level up
                    System.out.println(Main.ANSI_YELLOW + "Monster " + m.getName() + " is defeated" + Main.ANSI_RESET);
                    //awards: money, exp, give to every hero
                    for (Hero h1 : heroTeam.getParty()) {
                        int moneyEarned = m.getLevel() * 100;
                        int expGained = m.getLevel() * 2;
                        System.out.println(Main.ANSI_YELLOW + h1.getName() + " earn money: " + moneyEarned + ", gain exp: " + expGained + Main.ANSI_RESET);
                        h1.setMoney(h.getMoney() + moneyEarned);
                        h1.setExperience(h.getExperience() + expGained);
                        h1.levelUp();
                    }
                    System.out.println("==================================================");
                }
                return true;
            } else {
                return false;
            }
        }
    }

    private boolean heroTeleport(Hero h){
        System.out.println("-------------------------------\n" +Main.ANSI_GREEN+
                "Choose the hero you want to teleport to:"+Main.ANSI_RESET);
        //Input validation: restrict user input between 0~sizeof(heroTeam)
        int choice = -1;
        do {
            System.out.println(Main.ANSI_GREEN+"Your party size is "+heroTeam.getParty().size()+". Please choose a hero to recruit:"+Main.ANSI_RESET);
            System.out.printf("Enter a number between %d and %d: ", 0, heroTeam.getParty().size()-1);
            while (!scanner.hasNextInt()) {
                String input = scanner.next();
                System.out.printf("\"%s\" is not a valid number. Please enter a number between %d and %d: ", input, 0, heroTeam.getParty().size());
            }
            choice = scanner.nextInt();
        } while (choice < 0 || choice > heroTeam.getParty().size()-1);
        return lvWorld.teleport(h, heroTeam.getParty().get(choice));

    }

    private boolean heroRecall(Hero h){
        //ask for confirmation
        System.out.println("Hero "+h.getName()+" will be recalled to its starting lane, " +
                h.printStartingLane()+" , are you sure? (y/n)");
        if(scanner.next().charAt(0)=='y'){
            System.out.println("Hero recalled");
            lvWorld.recall(h);
            return true;
        }
        else{
            System.out.println("Recall canceled");
            return false;
        }
    }

    //a function to
    private boolean reachNexus(){
        for(Monster m: this.monsterTeam.getParty()){
            if (m.getR()==7)
                return true;
        }
        for(Hero h:this.heroTeam.getParty()){
            if (h.getR()==0)
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

    private void spawnMonster(int num, int averageHeroLevel){
        for(int i=0; i<num; i++){
            if(this.monsterList.size()==0)
                //read a new monsterList when the previous one became empty
                readMonster();
            Monster chosen = this.monsterList.get(0);
            this.monsterTeam.getParty().add(chosen);
            //todo scale monster stats according to hero average level
            chosen.setHealth(averageHeroLevel*200);
            //initialize monster starting position
            switch (i){
                //top lane
                case 0:
                    if (lvWorld.board[0][1].getM()==null){
                        chosen.makeMove(0, 1);
                        lvWorld.board[0][1].addMonster(chosen);
                    }
                    break;
                //mid lane
                case 1:
                    if (lvWorld.board[0][4].getM()==null){
                        chosen.makeMove(0, 4);
                        lvWorld.board[0][4].addMonster(chosen);
                    }
                    break;
                //bottom lane
                case 2:
                    if(lvWorld.board[0][7].getM()==null){
                        chosen.makeMove(0, 7);
                        lvWorld.board[0][7].addMonster(chosen);
                    }
                    break;
            }
            System.out.println(Main.ANSI_RED+"New monster "+chosen.getName()+" is spawned!"+Main.ANSI_RESET);
            this.monsterList.remove(chosen);
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

    //team info
    public void showInfo() {
        System.out.println("==================================================\nHero Team info:\n" +
                heroTeam.toString() +
                "==================================================");
        //todo print inventories?
        System.out.println("==================================================\nMonster Team info:\n" +
                monsterTeam.toString() +
                "==================================================");

    }
//
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

