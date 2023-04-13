import java.util.List;

public class HeroTeam extends Team<Hero>{
    public HeroTeam(List<Hero> chosenHeroes){
        super(chosenHeroes);
    }

    @Override
    public List<Hero> getParty() {
        return this.party;
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

    public int getAverageLevel(){
        int count = 0;
        for(Hero h: party){
            count += h.getLevel();
        }
        return count/party.size();
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
