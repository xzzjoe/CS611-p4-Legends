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
    public void showInfo() {

    }
}
