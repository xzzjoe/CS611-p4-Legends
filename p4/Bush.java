import java.util.Random;

public class Bush extends Space{
    private int buff;
    public Bush(){
        super();
        this.mark = 'B';
        this.buff = calcBuff();
    }

    public int calcBuff(){
        Random rand = new Random();
        return rand.nextInt(10) + 10;

    }

    public int getBuff(){
        return this.buff;
    }

    @Override
    public void addHero(Hero h){
        this.h = h;
        h.dexBuff(this.buff);
    }

    @Override
    public void removeHero(){
        this.h.dexDebuff(this.buff);
        this.h = null;
    }

}
