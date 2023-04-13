public class Armor extends Item implements Equipabble{
    private final int level;
    private final int damageReduction;
    private boolean equipped;
    public Armor(String name, int cost, int level, int damageReduction){
        super(name, cost);
        this.level = level;
        this.damageReduction = damageReduction;
        this.equipped = false;
    }

    public boolean equip(Hero h){
        if(h.level < this.level){
            System.out.println("Hero level not enough to equip armor " + this.name);
            return false;
        }
        this.equipped = true;
        return true;
    }

    public void unequip(){
        this.equipped = false;
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