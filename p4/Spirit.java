public class Spirit extends Monster{
    public Spirit(String name, int level, int damage, int defence, int dodge_chance) {
        super(name, level, damage, defence, dodge_chance);
    }
    @Override
    public String toString() {
        return "Spirit{" +
                "name=\"" + name + "\"" +
                ", level=" + level +
                ", health=" + health +
                ", damage=" + damage +
                ", defence=" + defence +
                ", dodge_chance=" + dodge_chance +
                "}";
    }
}
