
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HeroLoader {

    //pass the type of Hero to this method, to achieve generic
    public static <T extends Hero> List<T> loadHeroes(String fileName, Class<T> heroType) {
        List<T> heroes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            // Skip the header row in the config files
            br.readLine();
            //check next line exist and is not empty
            while ((line = br.readLine()) != null && !line.equals("")) {
                String[] parts = line.trim().split("\\s+");

                String name = parts[0];
                int mana = Integer.parseInt(parts[1]);
                int strength = Integer.parseInt(parts[2]);
                int agility = Integer.parseInt(parts[3]);
                int dexterity = Integer.parseInt(parts[4]);
                int money = Integer.parseInt(parts[5]);
                int experience = Integer.parseInt(parts[6]);

                T hero;
                if (heroType.equals(Paladin.class)) {
                    hero = heroType.cast(new Paladin(name, mana, strength, agility, dexterity, money, experience));
                }
                else if (heroType.equals(Warrior.class)) {
                    hero = heroType.cast(new Warrior(name, mana, strength, agility, dexterity, money, experience));
                }
                else {
                    hero = heroType.cast(new Sorcerer(name, mana, strength, agility, dexterity, money, experience));
                }
                heroes.add(hero);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return heroes;
    }
}

