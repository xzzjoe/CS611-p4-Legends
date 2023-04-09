import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellLoader {

    //pass the type of Spell to this method, to achieve generic
    public static <T extends Spell> List<T> loadSpells(String fileName, Class<T> spellType) {
        List<T> spells = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the header row in the config files
            br.readLine();
            //check next line exist and is not empty
            while ((line = br.readLine()) != null && !line.equals("")) {
                String[] parts = line.trim().split("\\s+");
                //name/cost/required level/damage/mana cost
                String name = parts[0];
                int cost = Integer.parseInt(parts[1]);
                int level = Integer.parseInt(parts[2]);
                int damage = Integer.parseInt(parts[3]);
                int mana_cost = Integer.parseInt(parts[4]);

                T spell;
                //manual type castings, to achieve generic in a static method
                if (spellType.equals(IceSpell.class)) {
                    spell = spellType.cast(new IceSpell(name, cost, level, damage, mana_cost));
                }
                else if (spellType.equals(FireSpell.class)) {
                    spell = spellType.cast(new FireSpell(name, cost, level, damage, mana_cost));
                }
                else {
                    spell = spellType.cast(new LightningSpell(name, cost, level, damage, mana_cost));
                }
                spells.add(spell);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return spells;
    }
}

