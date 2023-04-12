public abstract class Spell extends Item{
    protected int level, damage, mana_cost;

    public Spell(String name, int cost, int level, int damage, int mana_cost){
        super(name, cost);
        this.level = level;
        this.damage = damage;
        this.mana_cost = mana_cost;

    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getMana_cost() {
        return mana_cost;
    }

    @Override
    public String toString() {
        return "Spell{" +
                "\"" + name + "\"" +
                ", cost=" + cost +
                ", level=" + level +
                ", damage=" + damage +
                ", mana_cost=" + mana_cost +
                "}";
    }

}
