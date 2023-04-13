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
        if (this.equipped){
            System.out.println(this.name+" has already been equipped to hero");
            return false;
        }else if(h.level < this.level){
            System.out.println("Hero level not enough to equip armor " + this.name);
            return false;
        }
        else if(h.getArmor()!=null){
            System.out.println("Hero already have an armor");
            return false;
        }
        h.setArmor(this);
        this.equipped = true;
        return true;
    }

    public void unequip(Hero h){
        h.setArmor();
        this.equipped = false;
    }

    public boolean checkEquipped(){
        return this.equipped;
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