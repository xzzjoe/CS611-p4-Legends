public class Potion extends Item{
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
