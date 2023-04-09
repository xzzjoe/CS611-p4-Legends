public class FireSpell extends Spell{
    public FireSpell(String name, int cost, int level, int damage, int mana_cost) {
        super(name, cost, level, damage, mana_cost);
    }
    @Override
    public String toString() {
        return "FireSpell{" +
                "\"" + name + "\"" +
                ", cost=" + cost +
                ", level=" + level +
                ", damage=" + damage +
                ", mana_cost=" + mana_cost +
                "}";
    }
}
