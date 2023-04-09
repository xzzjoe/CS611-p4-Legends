import java.util.List;

public abstract class Team<T extends GameCharacter> {
    List<T> party;

    public Team(List<T> party) {
        this.party = party;
    }

    public List<T> getParty() {
        return party;
    }

    public boolean isAlive(){
        int teamHealth = 0;
        for(T t: party){
            teamHealth += t.getHealth();
        }
        if (teamHealth == 0)
            return false;
        else
            return true;
    }
}
