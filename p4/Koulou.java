import java.util.Random;

public class Koulou extends Space{
    private int buff;
    public Koulou() {
        super();
        this.mark = 'K';
        this.buff = calcBuff();
    }
    public int calcBuff(){
        Random rand = new Random();
        return rand.nextInt(10) + 10;

    }

    public int getBUff(){
        return this.buff;
    }

    @Override
    public void addHero(Hero h){
        this.h = h;
        h.strengthBuff(this.buff);
    }

    @Override
    public void removeHero(){
        this.h.strengthDebuff(this.buff);
        this.h = null;
    }
}
