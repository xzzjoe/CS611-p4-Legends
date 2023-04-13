public abstract class Space {
    char mark;
    protected Hero h;
    protected Monster m;



    public Space(){
        this.h = null;
        this.m = null;
    }
    public void greet(GameCharacter target){
        System.out.println("This is a space. For debugging. This line shouldn't be printed");
    }

    public Hero getH() {
        return h;
    }

    public Monster getM() {
        return m;
    }
    public void addHero(Hero h){ this.h = h;}


    public void addMonster(Monster m){ this.m = m;}

    public void removeHero(){this.h = null;}

    public void removeMonster(){this.m = null;}

    public String toString(){
        String ret;
        if(this.h != null & this.m != null){
            ret =  Main.ANSI_BLUE + "H&M" + Main.ANSI_RESET;
        }
        else if (this.h != null){
            ret =  Main.ANSI_BLUE + " H " + Main.ANSI_RESET;
        }
        else if(this.m != null){
            ret =  Main.ANSI_BLUE + " M " + Main.ANSI_RESET;
        }
        else{
            ret = Main.ANSI_RED + " " + this.mark + " " + Main.ANSI_RESET;
        }
        return ret;
    }
}
