//A turn-based battle between 2 teams

import java.util.Scanner;

public class Battle {
    private HeroTeam heroTeam;
    private MonsterTeam monsterTeam;
    private Scanner scanner;

    public Battle(HeroTeam heroTeam, MonsterTeam monsterTeam) {
        this.heroTeam = heroTeam;
        this.monsterTeam = monsterTeam;
        monsterTeam.scaleStats(heroTeam.getAverageLevel());
        this.scanner = new Scanner(System.in);
    }

    public void startBattle() {
        while (true) {
            System.out.println("/*******************Heroes' Turn*****************/");
            for(Hero h: heroTeam.getParty()){
                System.out.println("/************************************************/");
                if (h.isAlive())
                    heroTurn(h);
                else{
                    System.out.println(Main.ANSI_RED +"It's " + h.getName() + "'s turn, but " +h.getName()+ " is fainted and cannot move!"+Main.ANSI_RESET);
                }
                if (!monsterTeam.isAlive()) {
                    return;
                }
            }
            System.out.println("/*****************Monsters' Turn*****************/");
            for(Monster m: monsterTeam.getParty()){
                System.out.println("/************************************************/");
                if (m.isAlive())
                    monsterTurn(m);
                else
                    System.out.println(Main.ANSI_RED +"It's " + m.getName() + "'s turn, but " +m.getName()+ " is defeated and cannot move!"+Main.ANSI_RESET);
                if (!heroTeam.isAlive()) {
                    return;
                }
            }
            //restore hp&mp after each round
            heroTeam.restoreHpAndMana();
        }
    }

    public void heroTurn(Hero h){
        boolean turnTaken = false;
        while (!turnTaken) {
            System.out.println(Main.ANSI_GREEN+"It's " + h.getName() + "'s turn.\n" +
                    "Please choose an action: \n" +
                    "1. Attack\n" +
                    "2. Cast spell\n" +
                    "3. Use potion\n" +
                    "4. Equip item\n" +
                    "5. Display stats (won't consume this turn)\n"+Main.ANSI_RESET);

            char input = scanner.next().charAt(0);
            int choice;
            try {
                choice = Integer.parseInt(String.valueOf(input));
            } catch (Exception e) {
                System.out.println("Input format is wrong, please try again:");
                continue;
            }
            int targetIndex;
            switch (choice) {
                case 1:
                    // Attack
                    //choose target
                    System.out.println(Main.ANSI_GREEN+"Choose a target (enter index): "+Main.ANSI_RESET);
                    for (Monster m : monsterTeam.getParty()) {
                        if (m.isAlive())
                            System.out.println(monsterTeam.getParty().indexOf(m) + ": " + m);
                    }
                    targetIndex = scanner.nextInt();
                    Monster target = monsterTeam.getParty().get(targetIndex);
                    //perform move
                    h.attack(target);
                    turnTaken = true;
                    break;

                case 2:
                    // choose spell
                    System.out.println(Main.ANSI_GREEN+"Choose a spell (enter index, or -1 to go back): "+Main.ANSI_RESET);
                    h.getHeroInventory().printItemsByType(IceSpell.class);
                    h.getHeroInventory().printItemsByType(FireSpell.class);
                    h.getHeroInventory().printItemsByType(LightningSpell.class);
                    int spellIndex = scanner.nextInt();
                    if (spellIndex >= 0) {
                        GameItem s = h.getFromInventory(spellIndex);
                        if (s instanceof Spell) {
                            //choose target
                            System.out.println(Main.ANSI_GREEN+"Choose a target (enter index): \n"+Main.ANSI_RESET);
                            for (Monster m : monsterTeam.getParty()) {
                                if (m.isAlive())
                                    System.out.println(monsterTeam.getParty().indexOf(m) + ": " + m);
                            }
                            targetIndex = scanner.nextInt();
                            Monster spellTarget = monsterTeam.getParty().get(targetIndex);
                            //perform move
                            h.castSpell((Spell) s, spellTarget);
                            turnTaken = true;
                        }
                    }
                    break;

                case 3:
                    // choose potion
                    System.out.println(Main.ANSI_GREEN+"Choose a potion (enter index, or -1 to go back): "+Main.ANSI_RESET);
                    h.getHeroInventory().printItemsByType(Potion.class);
                    int potionIndex = scanner.nextInt();
                    if (potionIndex >= 0) {
                        GameItem p = h.getFromInventory(potionIndex);
                        if (p instanceof Potion) {
                            //choose target
                            System.out.println(Main.ANSI_GREEN+"Choose a target (enter index): \n"+Main.ANSI_RESET);
                            for(Hero h1: heroTeam.getParty()){
                                if(h1.isAlive())
                                    System.out.println(heroTeam.getParty().indexOf(h1) + ": " + h1);
                            }
                            targetIndex = scanner.nextInt();
                            Hero potionTarget = heroTeam.getParty().get(targetIndex);
                            //perform move
                            h.usePotion((Potion)p, potionTarget);
                            turnTaken = true;
                        }
                    }
                    break;

                case 4:
                    // choose equipment
                    System.out.println(Main.ANSI_GREEN+"Choose an equippable item (enter an index, or -1 to go back): \n"+Main.ANSI_RESET);
                    h.getHeroInventory().printItemsByType(Weapon.class);
                    h.getHeroInventory().printItemsByType(Armor.class);
                    int itemIndex = scanner.nextInt();
                    if (itemIndex >= 0) {
                        GameItem e = h.getFromInventory(itemIndex);
                        if (e instanceof Weapon) {
                            h.setWeapon((Weapon) e);
                            turnTaken = true;
                        } else if(e instanceof Armor){
                            h.setArmor((Armor) e);
                            turnTaken = true;
                        }
                        else{
                            System.out.println("Input is wrong, please try again");
                        }
                    }
                    break;

                case 5:
                    displayInfo();
                    break;

                default:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }

    }

    public void monsterTurn(Monster m){
        for(Hero h: heroTeam.getParty()){
            if(h.isAlive()){
                m.attack(h);
                break;
            }
        }

    }

    public void displayInfo(){
        System.out.println("==================================================\nHero Team info:\n" +
                heroTeam.toString() +
                "Monster Team info:\n" +
                monsterTeam.toString() +
                "==================================================");
    }
    public void finishBattle(){
        //TODO for count of defeated monsters in this battle:
        int count = 0;
        for(Monster m: monsterTeam.getParty()){
            if(!m.isAlive())
                count++;
        }
        heroTeam.increaseMonsterDefated(count);
        if (heroTeam.isAlive()) {
            //if win: earn money + exp and level up, and revive
            System.out.println(Main.ANSI_YELLOW+"All monsters defeated, battle won"+Main.ANSI_RESET);
            //awards: money, exp
            for(Hero h: heroTeam.getParty()){
                int moneyEarned = monsterTeam.getAverageLevel()*100;
                int expGained = monsterTeam.getAverageLevel()*2;
                System.out.println(Main.ANSI_YELLOW+"All combatants earn money: "+moneyEarned+", gain exp: "+expGained+Main.ANSI_RESET);
                h.setMoney(h.getMoney()+moneyEarned);
                h.setExperience(h.getExperience()+expGained);
                h.levelUp();
            }
            heroTeam.autoRevive();
            System.out.println("==================================================");
        }
    }
}
