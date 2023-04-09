
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MonsterLoader {

    //pass the type of Monster to this method, to achieve generic
    public static <T extends Monster> List<T> loadMonsters(String fileName, Class<T> monsterType) {
        List<T> monsters = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip the header row in the config files
            br.readLine();
            //check next line exist and is not empty
            while ((line = br.readLine()) != null && !line.equals("")) {
                String[] parts = line.trim().split("\\s+");

                String name = parts[0];
                int level = Integer.parseInt(parts[1]);
                int damage = Integer.parseInt(parts[2]);
                int defence = Integer.parseInt(parts[3]);
                int dodge_chance = Integer.parseInt(parts[4]);

                T monster;
                //manual type castings, to achieve generic in a static method
                if (monsterType.equals(Spirit.class)) {
                    monster = monsterType.cast(new Spirit(name, level, damage, defence, dodge_chance));
                }
                else if (monsterType.equals(Dragon.class)) {
                    monster = monsterType.cast(new Dragon(name, level, damage, defence, dodge_chance));
                }
                else {
                    monster = monsterType.cast(new Exoskeleton(name, level, damage, defence, dodge_chance));
                }
                monsters.add(monster);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return monsters;
    }
}

