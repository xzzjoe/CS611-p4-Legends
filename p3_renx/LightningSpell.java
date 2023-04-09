public class LightningSpell extends Spell{

    public LightningSpell(String name, int cost, int level, int damage, int mana_cost) {
        super(name, cost, level, damage, mana_cost);
    }
    @Override
    public String toString() {
        return "LightningSpell{" +
                "\"" + name + "\"" +
                ", cost=" + cost +
                ", level=" + level +
                ", damage=" + damage +
                ", mana_cost=" + mana_cost +
                "}";
    }
}
