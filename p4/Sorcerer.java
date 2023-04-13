public class Sorcerer extends Hero{
    public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int money, int experience) {
        super(name, mana, strength, agility, dexterity, money, experience);
    }
    @Override
    public String toString() {
        return "Sorcerer\n{" +
                "name=\"" + name + "\"" +
                ", \nlevel=" + level +
                ", health=" + health +
                ", mana=" + mana +
                ", \nstrength=" + strength +
                ", agility=" + agility +
                ", dexterity=" + dexterity +
                ", \nmoney=" + money +
                ", experience=" + experience +
                ", \nweapon=" + printWeapon() +
                ", \narmor=" + printArmor() +
                ", \nstartingLane=" + printStartingLane()+
                "}";
    }
}
