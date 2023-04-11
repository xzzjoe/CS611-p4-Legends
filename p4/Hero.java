import static java.lang.Math.round;
//TODO temporarily non-abstract

public class Hero extends GameCharacter{
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;
    protected int money;
    protected int experience;
    protected int level;
//    private Weapon weapon;
//    private Armor armor;
//    private HeroInventory heroInventory;

    public Hero(String name, int row, int col, int mana, int strength, int agility, int dexterity, int money, int experience) {
        this.name = name;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;
        this.money = money;
        this.experience = experience;
        // level = experience % 10 +1
        // health = level*100
        this.level = (experience/10)+1;
        this.health = level*100;
//        this.heroInventory = new HeroInventory();

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
//                ", \nweapon=" + getWeapon() +
//                ", \narmor=" + getArmor() +
                "}";
    }

    /* disabled: inventory related functions

    public void addToInventory(GameItem item){
        heroInventory.gameItemList.add(item);
    }
    public GameItem removeFromInventory(int index){
        GameItem item = heroInventory.gameItemList.get(index);
        heroInventory.gameItemList.remove(item);
        return item;
    }

    public GameItem getFromInventory(int index){
        GameItem item = heroInventory.gameItemList.get(index);
        heroInventory.gameItemList.remove(item);
        return item;
    }

    public String printAllItems(){
        String info = "";
        if(heroInventory.gameItemList==null)
            return "This hero's inventory is empty";
        for (GameItem g : heroInventory.gameItemList) {
            info += (heroInventory.gameItemList.indexOf(g) + ": " + g + "\n");
        }
        return info;
    }

    public String getWeapon() {
        if (this.weapon == null){
            return "Unequipped";
        }
        else
            return weapon.toString();
    }


    public void setWeapon(Weapon weapon) {
        if (this.weapon != null){
            System.out.println("Already had "+getWeapon());
        } else if (weapon.getLevel()>level) {
            System.out.println("Does not satisfy hero level requirement");
        } else{
            this.weapon = weapon;
            System.out.println(Main.ANSI_YELLOW+"New Weapon equipped:\n"+getWeapon()+Main.ANSI_RESET);
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

    public String getArmor() {
        if (this.armor == null){
            return "Unequipped";
        }
        else
            return armor.toString();
    }

    public void setArmor(Armor armor) {
        if(this.armor!=null){
            System.out.println("Already had "+getArmor());
        } else if (armor.getLevel()>level) {
            System.out.println("Does not satisfy hero level requirement");
        } else{
            this.armor = armor;
            System.out.println(Main.ANSI_YELLOW+"New Armor equipped:\n"+getArmor()+Main.ANSI_RESET);
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

     */
    public void levelUp(){
        while(experience>=10){
            experience-=10;
            level+=1;
            System.out.println(Main.ANSI_YELLOW +name+" level up!"+Main.ANSI_RESET);
            health = level*100;
        }
    }

    public void attack(Monster target) {
        //TODO use formula, defence etc.
        // Implement hero attack logic
        System.out.println(name+" attacked "+target.getName());
        if(weapon!=null){
            target.takeDamage((int) round(strength*1.7) + weapon.getDamage());
        }
        else {
            target.takeDamage((int) round(strength*1.7));
        }
    }

    public void takeDamage(int damage){
        //TODO use formula, defence etc.
        System.out.println(Main.ANSI_RED+name+" received "+damage+"damage!"+Main.ANSI_RESET);
        setHealth(getHealth()-damage);
    }

    /* Advanced battle
    public void castSpell(Spell spell, Monster target) {
        if (spell.getLevel()>level &&spell.getMana_cost()>mana) {
            System.out.println("Does not satisfy requirements to use");
        }
        else{
            System.out.println(Main.ANSI_YELLOW+name+" casted a "+ spell.getName()+" on " +target.getName()+Main.ANSI_RESET);
            //todo spell effect
            target.takeDamage(spell.damage);
        }
    }

    public void usePotion(Potion potion, Hero target) {
        if (potion.getLevel()>level) {
            System.out.println("Does not satisfy requirements to use");
        }else {
            System.out.println(Main.ANSI_YELLOW+name + " used a " + potion.getName() + " on " + target.getName()+Main.ANSI_RESET);
            //todo potion effect
            target.setHealth(target.getHealth()+potion.getAttribute_increase());
        }
    }
    */

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

}

