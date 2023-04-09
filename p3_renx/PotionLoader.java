import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// A static class for reading Potions
class PotionLoader {
    public static List<Potion> loadPotions(String filePath) {
        //a List to store all loaded Potions
        List<Potion> potions = new ArrayList<>();

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
                int attribute_increase = Integer.parseInt(parts[3]);
                String attribute_affected = parts[4];

                Potion potion = new Potion(name, cost, level, attribute_increase, attribute_affected);
                potions.add(potion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return potions;
    }
}
