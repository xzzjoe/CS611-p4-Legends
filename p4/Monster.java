import java.util.Random;

import static java.lang.Math.round;

//TODO temporarily non-abstract
public class Monster extends GameCharacter{

    protected int level;
    protected int damage;
    protected int defence;
    protected int dodge_chance;

    public Monster(String name, int level, int damage, int defence, int dodge_chance) {
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.defence = defence;
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
                ", defence=" + defence +
                ", dodge_chance=" + dodge_chance +
                "}";
    }


    public void attack(Hero target) {
        System.out.println(name+" attacked "+target.getName());
        target.takeDamage((int) round(damage*0.5));
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

    public int getLevel() {
        return level;
    }
}
