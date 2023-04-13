import java.util.List;

public class MonsterTeam extends Team<Monster>{
    public MonsterTeam(List<Monster> monsters){
        super(monsters);
    }

    @Override
    public List<Monster> getParty() {
        return this.party;
    }

    @Override
    public String toString() {
        String info = "";
        for (Monster m : this.party) {
            info += this.party.indexOf(m) + ": " + m.toString() +"\n";
        }
        return info;
    }
}
