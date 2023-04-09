public class IceSpell extends Spell{
    public IceSpell(String name, int cost, int level, int damage, int mana_cost) {
        super(name, cost, level, damage, mana_cost);
    }
    @Override
    public String toString() {
        return "IceSpell{" +
                "\"" + name + "\"" +
                ", cost=" + cost +
                ", level=" + level +
                ", damage=" + damage +
                ", mana_cost=" + mana_cost +
                "}";
    }
}
