//TODO same with previous

public abstract class GameCharacter {
    protected String name;
    protected int health;
    protected int r;
    protected int c;

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

    public void makeMove(int r, int c){
        // ValorWorld will check if we can move or not
        this.r = r;
        this.c = c;

    }

    public boolean isAlive(){
        if (health==0)
            return false;
        else
            return true;
    }

    public int getR(){
        return this.r;
    }

    pubic int getC()
    {
        return this.c;
    }}
