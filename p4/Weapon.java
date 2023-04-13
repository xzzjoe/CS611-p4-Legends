public class Weapon extends Item implements Equipabble{
    private int level;
    private int damage;
    private int requiredHands;
    private boolean equipped;

    public Weapon(String name, int cost, int level, int damage, int requiredHands) {
        super(name, cost);
        this.level = level;
        this.damage = damage;
        this.requiredHands = requiredHands;
        this.equipped = false;
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


    public boolean equip(Hero h){
        if (this.equipped){
            System.out.println(this.name+" has already been equipped to hero");
            return false;
        }else if(h.level < this.level){
            System.out.println("Hero level not enough to equip weapon " + this.name);
            return false;
        }
        else if(h.getWeapon()!=null){
            System.out.println("Hero already have a weapon");
            return false;
        }
        h.setWeapon(this);
        this.equipped = true;
        return true;
    }

    public void unequip(Hero h){
        h.setWeapon();
        this.equipped = false;
    }

    public boolean checkEquipped(){
        return this.equipped;
    }
}
