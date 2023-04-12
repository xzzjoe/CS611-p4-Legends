public class Weapon extends Item implements Equipabble{
    private int level;
    private int damage;
    private int requiredHands;

    public Weapon(String name, int cost, int level, int damage, int requiredHands) {
        this.name = name;
        this.cost = cost;
        this.level = level;
        this.damage = damage;
        this.requiredHands = requiredHands;
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

    public int getDamage() {
        return damage;
    }

    public int getRequiredHands() {
        return requiredHands;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "\"" + name + "\"" +
                ", cost=" + cost +
                ", level=" + level +
                ", damage=" + damage +
                ", requiredHands=" + requiredHands +
                "}";
    }


}
