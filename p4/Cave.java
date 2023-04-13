import java.util.Random;
public class Cave extends Space{
    private int buff;
    public Cave(){
        super();
        this.mark = 'C';
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
        h.agilBuff(this.buff);
    }

    @Override
    public void removeHero(){
        this.h.agilDebuff(this.buff);
        this.h = null;
    }



}
