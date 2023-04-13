public abstract class Spell extends Item {
    protected int level, damage, mana_cost;

    public Spell(String name, int cost, int level, int damage, int mana_cost){
        super(name, cost);
        this.level = level;
        this.damage = damage;
        this.mana_cost = mana_cost;

    }

    public void cast(Hero h, Monster m){
        if(h.level < this.level){
            System.out.println("Level not enough.");
            return;
        }
        if(h.mana < this.mana_cost){
            System.out.println("Not enough mana");
            return;
        }
        h.mana -= mana_cost;
        m.takeDamage(this.damage);
        System.out.println("Hero " + h.name + " cast a spell onto monster " + m.name);
        if (this instanceof FireSpell){
            m.reduceDamage();
        } else if (this instanceof IceSpell) {
            m.reduceDefense();
        }else{
            m.reduceDodge();
        }

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

    public void consume(Hero h){

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
