import java.lang.Math;
import java.util.Random;

import static java.lang.Math.*;

public abstract class Hero extends GameCharacter{
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;
    protected int money;
    protected int experience;
    protected int level;
    protected int reviveCounter;
    protected int def;
    private Weapon weapon;
    private Armor armor;
    //startingLane can be 't' 'm' 'b', storing the original lane of a hero
    protected char startingLane;
    private HeroInventory heroInventory;

    public Hero(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.experience = experience;
        this.reviveCounter = -1;
        // level = experience % 10 +1
        // health = level*100
        this.level = (experience/10)+1;
        this.health = level*100;
        this.def = 0;
        this.heroInventory = new HeroInventory();

    }

    //TODO Add any additional methods, getters, or setters here as needed.
    //i.e. change data fields to private, and create setter methods for sub classes to use

    @Override
    public String toString() {
        return "Hero{" +
                "name=\"" + name + "\"" +
                ", level=" + level +
                ", health=" + health +
                ", mana=" + mana +
                ", \nstrength=" + strength +
                ", agility=" + agility +
                ", dexterity=" + dexterity +
                ",\n money=" + money +
                ", experience=" + experience +
                "}";
    }

    // disabled: inventory related functions

    public void addToInventory(Item item){
        heroInventory.itemList.add(item);
    }

    //probably useless, since we have new item function in item classes
    public Item removeFromInventory(int index){
        Item item = heroInventory.itemList.get(index);
        heroInventory.itemList.remove(item);
        return item;
    }

    public Item getFromInventory(int index){
        Item item = heroInventory.itemList.get(index);
        return item;
    }

    public String printAllItems(){
        String info = "";
        if(heroInventory.itemList==null)
            return "This hero's inventory is empty";
        for (Item g : heroInventory.itemList) {
            info += (heroInventory.itemList.indexOf(g) + ": " + g + "\n");
        }
        return info;
    }

    public String printWeapon() {
        if (this.weapon == null){
            return "Unequipped";
        }
        else
            return weapon.toString();
    }

    public Armor getArmor(){
        return this.armor;
    }
    public Weapon getWeapon(){
        return this.weapon;
    }

    public void setWeapon(Weapon weapon) {
        if (this.weapon != null){
            System.out.println("Already had "+ printWeapon());
        } else if (weapon.getLevel()>level) {
            System.out.println("Does not satisfy hero level requirement");
        } else{
            this.weapon = weapon;
            System.out.println(Main.ANSI_YELLOW+"New Weapon equipped:\n"+ printWeapon()+Main.ANSI_RESET);
        }
    }
    public void setWeapon() {
        if (this.weapon == null){
            System.out.println("This hero have no weapon");
        }
        else {
            this.weapon = null;
            System.out.println(Main.ANSI_YELLOW+"Weapon removed"+Main.ANSI_RESET);
        }
    }

    public String printArmor() {
        if (this.armor == null){
            return "Unequipped";
        }
        else
            return armor.toString();
    }

    public void setArmor(Armor armor) {
        if(this.armor!=null){
            System.out.println("Already had "+ printArmor());
        } else if (armor.getLevel()>level) {
            System.out.println("Does not satisfy hero level requirement");
        } else{
            this.armor = armor;
            System.out.println(Main.ANSI_YELLOW+"New Armor equipped:\n"+ printArmor()+Main.ANSI_RESET);
        }
    }
    public void setArmor() {
        if (this.armor == null){
            System.out.println("This hero have no armor");
        }
        else {
            this.armor = null;
            System.out.println(Main.ANSI_YELLOW+"Armor removed"+Main.ANSI_RESET);
        }
    }

    public HeroInventory getHeroInventory() {
        return heroInventory;
    }

     //
    public void levelUp(){
        while(experience>=10){
            experience-=10;
            level+=1;
            System.out.println(Main.ANSI_YELLOW +name+" level up!"+Main.ANSI_RESET);
            health = level*100;
        }
    }

    public void dexBuff(int val){
        //Temp buff, need to debuff when exiting space
        this.dexterity += val;
    }

    public void strengthBuff(int val){
        this.strength += val;
    }

    public void agilBuff(int val){
        this.agility += val;
    }

    public void dexDebuff(int val){
        this.dexterity -= val;
    }

    public void strengthDebuff(int val){
        this.strength -= val;
    }

    public void agilDebuff(int val){
        this.agility -= val;
    }

    public void attack(Fightable F) {
        //TODO use formula, defence etc.
        // Implement hero attack logic
        Monster m = (Monster) F;
        System.out.println(name+" attacked "+m.getName());
        if(weapon!=null){
            m.takeDamage((int) round(strength*1.7) + weapon.getDamage());
        }
        else {
            m.takeDamage((int) round(strength*1.7));
        }
    }

    public double calcDogde() {
        double res = Math.log(this.agility)/20;
        return res;
    }
    public void takeDamage(int damage){
        //TODO use formula, defence etc.
        Random rand = new Random();
        double chance = rand.nextDouble();
        double dodgeChance = calcDogde();
        System.out.println("Dodge chance for hero " + this.name  + " is " + dodgeChance);
        if(chance < dodgeChance){
            System.out.println("Hero " +this.name + "has dodged the incoming attack!");
            return;
        }
        int reduction = this.def;
        if(this.armor != null){
            reduction += this.armor.getDamageReduction();
        }
        int trueDamage = damage - reduction;
        System.out.println(Main.ANSI_RED+name+" received "+trueDamage+"damage!"+Main.ANSI_RESET);
        if(this.health <= trueDamage){
            Main.slash.play();
            try{
                Main.slash.resetAudioStream();
            }catch(Exception e){
                System.out.println("Could not reset audio exception");
            }
            this.setReviveCounter(2);
        }
        setHealth(getHealth()-trueDamage);
    }



    public boolean revive(){
        //Return True if is alive/revived , false if dead
        if(this.reviveCounter < 0){
            System.out.println("rcytfu");
            return false;
        }
        else if(this.reviveCounter == 0){
            System.out.println("Hero " +this.name + Main.ANSI_RED +" is revived! "+ Main.ANSI_RESET );
            this.reviveCounter = -1;
            Main.respawn.play();
            try{
                Main.respawn.resetAudioStream();
            }catch(Exception e){
                System.out.println("Could not reset audio exception");
            }
            return true;
        }
        else{
            System.out.println("Hero " +this.name + " still has "+Main.ANSI_RED + this.reviveCounter  + Main.ANSI_RESET + " rounds to be revived.");
            reviveCounter--;
            return false;
        }
    }




    //todo castSpell and usePotion on Hero side

    // Advanced battle
    public boolean castSpell(Spell spell, Monster target) {
        if(spell.cast(this, target)){
            System.out.println(Main.ANSI_YELLOW);
            removeFromInventory(heroInventory.itemList.indexOf(spell));
            System.out.println(Main.ANSI_RESET);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean usePotion(Potion potion) {
        if(potion.consume(this)){
            System.out.println(Main.ANSI_YELLOW);
            removeFromInventory(heroInventory.itemList.indexOf(potion));
            System.out.println(Main.ANSI_RESET);
            return true;
        }
        else{
            return false;
        }
    }



    public void setReviveCounter(int rounds){
        this.reviveCounter = rounds;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean sameLane(Hero h){
        if(this.r == h.getR() || this.r + 1 == h.getR() || this.r - 1 == h.getR()){
            return true;
        }
        return false;
    }

    public int distance(GameCharacter c){
        //l2 distance between two heroes
        double rDiff =(double)(c.r - this.r);
        double cDiff = (double)(c.c - this.c);
        int dist = (int)floor(sqrt((pow(rDiff, 2.0) + pow(cDiff, 2.0))));
        return dist;
    }

    public char getStartingLane() {
        return startingLane;
    }

    public String printStartingLane() {
        if(this.startingLane=='t')
            return "Top Lane";
        else if(this.startingLane=='m')
            return "Mid Lane";
        else if(this.startingLane=='b')
            return "Bottom Lane";
        else
            return "Unassigned";
    }

    public void setStartingLane(char startingLane) {
        this.startingLane = startingLane;
    }

    public void respawn(){
        this.r = 7;
        if(this.startingLane=='t')
            this.c = 0;
        else if(this.startingLane=='m')
            this.c = 3;
        else
            this.c = 6;
        //todo default HP MP and other values
        this.health = this.level*100;
        this.mana = 100;
    }

}

