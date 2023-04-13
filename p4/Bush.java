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

    public int getBUff(){
        return this.buff;
    }

}
