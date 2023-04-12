
public class Item implements Tradeable{
    protected int value;
    protected String name;


    public Item(String name, int val) {
//		Value represent monetary value, stats for subclass use, can leave as 0 if no use
        this.value = val;
        this.name = name;
    }


    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public int purchasePrice(){
        return this.getValue();
    }

    public int sellPrice(){
        return this.value/2;
    }
    public String heroSell() {
        String ret = this.name + " can be sold for " + (this.value/2);
        return ret;
    }





    @Override
    public boolean equals(Object obj){
        if (this.getClass() == obj.getClass()) {
            return this.name == ((Item) obj).getName();
        }
        return false;
    }






}
