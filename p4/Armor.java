public class Armor extends Item{
    private final int level;
    private final int damageReduction;
    public Armor(String name, int cost, int level, int damageReduction){
        super(name, cost);
        this.level = level;
        this.damageReduction = damageReduction;
    }

    public int getLevel() {
        return level;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    @Override
    public String toString() {
        return "Armor{name=" + name +
                ", level=" + level +
                ", damageReduction=" + damageReduction +
                ", cost=" + cost +
                 '\'' + '}';
    }
}