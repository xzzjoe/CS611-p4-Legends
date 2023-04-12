import java.util.List;

public abstract class Team<T extends GameCharacter> {

    List<T> party;
    public Team(List<T> party) {
        this.party = party;
    }

    public abstract List<T> getParty();

    public abstract void showInfo();
}
