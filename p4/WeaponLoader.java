import java.io.*;
import java.util.ArrayList;
import java.util.List;

// A static class for reading Weapons
class WeaponLoader {
    public static List<Weapon> loadWeapons(String filePath) {
        //a List to store all loaded weapons
        List<Weapon> weapons = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            br.readLine(); // Skip the header row in the config files
            //check next line exist and is not empty
            while ((line = br.readLine()) != null && !line.equals("")) {
                //use a regular expression to match
                String[] parts = line.trim().split("\\s+");
                String name = parts[0];
                int cost = Integer.parseInt(parts[1]);
                int level = Integer.parseInt(parts[2]);
                int damage = Integer.parseInt(parts[3]);
                int requiredHands = Integer.parseInt(parts[4]);

                Weapon weapon = new Weapon(name, cost, level, damage, requiredHands);
                weapons.add(weapon);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weapons;
    }
}
