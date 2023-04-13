public class Potion extends Item implements Consumable{
    private int level;
    private int attribute_increase;
    private String attribute_affected;

    public Potion(String name, int cost, int level, int attribute_increase, String attribute_affected){
        super(name, cost);
        this.level = level;
        this.attribute_increase = attribute_increase;
        this.attribute_affected = attribute_affected;
    }


    public int getLevel() {
        return level;
    }


    public int getAttribute_increase() {
        return attribute_increase;
    }

    public String getAttribute_affected() {
        return attribute_affected;
    }


    public boolean consume(Hero h){
        if(this.level > h.level){
            System.out.println("Hero's level not enough to consume this potion");
            return false;
        }
        if(this.attribute_affected.contains("All")){
            h.health += this.attribute_increase;
            h.mana += this.attribute_increase;
            h.strength += this.attribute_increase;
            h.dexterity += this.attribute_increase;
            h.agility += this.attribute_increase;
            h.def += this.attribute_increase;
            System.out.println("All stats increased by " + this.attribute_affected);
            return true;
        }
        if(this.attribute_affected.contains("Health")){
            h.health += this.attribute_increase;
            System.out.println("Health increased by " + this.attribute_affected);

        }
        if(this.attribute_affected.contains("Mana")){
            h.mana += this.attribute_increase;
            System.out.println("Mana increased by " + this.attribute_affected);
        }
        if(this.attribute_affected.contains("Agility")){
            h.agility += this.attribute_increase;
            System.out.println("Agility increased by " + this.attribute_affected);
        }
        if(this.attribute_affected.contains("Strength")){
            h.strength += this.attribute_increase;
            System.out.println("Strength increased by " + this.attribute_affected);
        }
        if(this.attribute_affected.contains("Dexterity")){
            h.dexterity += this.attribute_increase;
            System.out.println("Dexterity increased by " + this.attribute_affected);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Potion{" +
                "\"" + name + "\"" +
                ", cost=" + cost +
                ", level=" + level +
                ", attribute_increase=" + attribute_increase +
                ", attribute_affected=" + attribute_affected +
                "}";
    }
}
