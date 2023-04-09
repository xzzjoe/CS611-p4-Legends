import java.util.List;

//use generic to define MonsterTeam
public class MonsterTeam extends Team<Monster>{
    public MonsterTeam(List<Monster> monsters){
        super(monsters);
    }
    @Override
    public String toString() {
        String info = "";
        for (Monster m : this.party) {
            info += this.party.indexOf(m) + ": " + m.toString() +"\n";
        }
        return info;
    }
    public int getAverageLevel(){
        int count = 0;
        for(Monster m: party){
            count += m.getLevel();
        }
        return (int)count/party.size();
    }

    public void scaleStats(int heroLevel){
        for(Monster m: party){
            //TODO adjust the scaling rule for monster stats
            m.setHealth(m.getHealth()*heroLevel/2);
        }
        System.out.println("Monster power scaled according to HeroTeam's average level of "+heroLevel);
    }
}
