public abstract class GameCharacter {
    protected String name;
    protected int health;

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        //minimal health is 0
        if (health<0)
            health=0;
        this.health = health;
    }

    public boolean isAlive(){
        if (health==0)
            return false;
        else
            return true;
    }
}
