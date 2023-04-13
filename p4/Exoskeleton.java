public class Exoskeleton extends Monster{
    public Exoskeleton(String name, int level, int damage, int defence, int dodge_chance) {
        super(name, level, damage, defence, dodge_chance);
    }
    @Override
    public String toString() {
        return "Exoskeleton{" +
                "name=\"" + name + "\"" +
                ", level=" + level +
                ", health=" + health +
                ", damage=" + damage +
                ", defense=" + defense +
                ", dodge_chance=" + dodge_chance +
                "}";
    }
}
