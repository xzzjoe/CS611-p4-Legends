public class Armor extends EquippableItem{

    private int level;
    private int damage_reduction;

    public Armor(String name, int cost, int level, int damage_reduction) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage_reduction = damage_reduction;
    }
    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getLevel() {
        return level;
    }
    public int getDamage_reduction() {
        return damage_reduction;
    }

    @Override
    public String toString() {
        return "Armor{" +
                "\"" + name + "\"" +
                ", cost=" + cost +
                ", level=" + level +
                ", damage_reduction=" + damage_reduction +
                "}";
    }
}
