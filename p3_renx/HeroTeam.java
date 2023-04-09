import java.util.List;
//use generic to define HeroTeam
public class HeroTeam extends Team<Hero>{
    private int row;
    private int col;
    private int monsterDefated = 0;
    private int spaceTravelled = 0;
    public HeroTeam(List<Hero> chosenHeroes){
        super(chosenHeroes);
        row = 0;
        col = 0;
    }

    public void moveTo(int newRow, int newCol){
        setRow(newRow);
        setCol(newCol);
    }
    public void restoreHpAndMana(){
        for(Hero h: party){
            if(h.isAlive()){
                //regen HP 20%, mp 20%
                int addHP = (int) (h.getHealth()*0.2);
                int addMP = (int) (h.getMana()*0.2);
                System.out.println(Main.ANSI_BLUE+"Auto-regen for "+ h.getName()+": HP+"+addHP+", MP+"+addMP+" applied!"+Main.ANSI_RESET);
                h.setHealth(h.getHealth()+addHP);
                h.setMana(h.getMana()+addMP);
            }
        }
    }
    public void autoRevive(){
        for(Hero h: party){
            if(!h.isAlive()){
                //revive with HP&MP
                int addHP = h.getLevel()*50;
                int addMP = 300;
                System.out.println(Main.ANSI_BLUE+"Auto-revive for "+ h.getName()+" after a battle!"+Main.ANSI_RESET);
                h.setHealth(h.getHealth()+addHP);
                h.setMana(addMP);
            }
        }
    }
    public int getAverageLevel(){
        int count = 0;
        for(Hero h: party){
            count += h.getLevel();
        }
        return (int)count/party.size();
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMonsterDefated() {
        return monsterDefated;
    }

    public void increaseMonsterDefated(int num) {
        this.monsterDefated += num;
    }

    public void increaseSpaceTravelled() {
        this.spaceTravelled += 1;
    }

    public int getSpaceTravelled() {
        return spaceTravelled;
    }

    @Override
    public String toString() {
        String info = "";
        for (Hero h : this.party) {
            info += this.party.indexOf(h) + ": " + h.toString() +"\n";
        }
        return info;
    }
}
