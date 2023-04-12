import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// A static class for reading Armors
class ArmorLoader {
    public static List<Armor> loadArmors(String filePath) {
        //a List to store all loaded Armors
        List<Armor> armors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header row in the config files
            //check next line exist and is not empty
            while ((line = br.readLine()) != null && !line.equals("")) {
                //use a regular expression to match
                String[] parts = line.trim().split("\\s+");
                String name = parts[0];
                int cost = Integer.parseInt(parts[1]);
                int level = Integer.parseInt(parts[2]);
                int damage_reduction = Integer.parseInt(parts[3]);

                Armor armor = new Armor(name, cost, level, damage_reduction);
                armors.add(armor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return armors;
    }
}
