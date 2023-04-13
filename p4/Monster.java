import java.util.Random;

import static java.lang.Math.round;

//TODO temporarily non-abstract
public class Monster extends GameCharacter implements Fightable{

    protected int level;
    protected int damage;
    protected int defense;
    protected int dodge_chance;

    public Monster(String name, int level, int damage, int defense, int dodge_chance) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defense = defense;
        this.dodge_chance = dodge_chance;
        this.health = level*100;
    }

    //TODO Add any additional methods, getters, or setters here as needed.
    //i.e. change data fields to private, and create setter methods for sub classes to use

    @Override
    public String toString() {
        return "Monster{" +
                "name=\"" + name + "\"" +
                ", level=" + level +
                ", health=" + health +
                ",\n damage=" + damage +
                ", defence=" + defense +
                ", dodge_chance=" + dodge_chance +
                "}";
    }


    public void attack(Fightable F) {
        Hero h = (Hero) F;
        System.out.println(name+" attacked "+h.getName());
        F.takeDamage((int) round(damage*0.5));
    }

    public void takeDamage(int damage){
        if(!dodge()){
            //TODO use formula
            System.out.println(Main.ANSI_RED+name+" received "+damage+"damage!"+Main.ANSI_RESET);
            setHealth(getHealth()-damage);
        }
        else
            System.out.println("But "+name+" dodged it!");
    }

    private boolean dodge(){
        Random random = new Random();
        //roll a die for dodge
        if (random.nextInt(100) < dodge_chance*0.5)
            return true;
        else
            return false;
    }

    public void reduceDamage() {
        this.damage = (int) (this.damage * 0.95);
    }

    public void reduceDefense() {
        this.defense = (int) (this.defense * 0.95);
    }

    public void reduceDodge() {
        this.dodge_chance = (int) (this.dodge_chance * 0.95);
    }

    public int getLevel() {
        return level;
    }
}
