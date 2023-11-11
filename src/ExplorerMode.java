import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ExplorerMode {

    private final GameData gameData;

    private final UserData currentUser;

    // if user wants to leave this mode
    private boolean shouldExit;
    private Map<String, String> combinations = new HashMap<>();

    public ExplorerMode(UserData userData, GameData gameData) throws IOException {
        this.combinations = readCombinationsFromFile();
        this.currentUser = userData;
        this.gameData = gameData;
    }

    // Playing mechanism
    public void start() throws IOException {
        Scanner scanner = new Scanner(System.in);
        shouldExit = false;

        while (!shouldExit) {
            int currentLevel = currentUser.getLevel();
            int currentExp = currentUser.getExp();

            // Display level, materials to combine and experience points
            System.out.println("User's level = " + currentLevel);
            System.out.println("Available materials = " + gameData.getMaterials(currentUser.getUsername()));
            System.out.println("User's experience points = " + currentExp);

            //system to upgrade level of the user
            if (currentExp >= 100) {
                currentUser.setLevel(currentLevel + 1);
                currentUser.setExp(currentExp - 100);
                System.out.println("Congratulations! You have leveled up to level " + currentUser.getLevel() + "!");
            }


            System.out.println("Rules:");
            System.out.println("Enter material combination (e.g. water + earth) or x to go back:");

            String input = scanner.nextLine();

            // option to go back
            if (input.equalsIgnoreCase("x")) {
                shouldExit = true;
            }

            // gaming process
            else {
                String[] materialsArray = input.split("\\s*\\+\\s*");

                // too many or not enough parameters(materials) to combine
                if (materialsArray.length != 2) {
                    System.out.println("Invalid combination! Please enter two materials separated by a plus sign.");
                    continue;
                }

                List<String> materials = Arrays.asList(materialsArray);

                if (!gameData.hasMaterial(currentUser.getUsername(), materials.get(0).toLowerCase()) || !gameData.hasMaterial(currentUser.getUsername(), materials.get(1).toLowerCase())) {
                    System.out.println("You do not have the necessary materials to create this item. Please try again.");
                    continue;
                }

                // do i need this line?
                System.out.println("Combination entered: " + materials.get(0) + " + " + materials.get(1));

                String newMaterial = combinations.get(materials.get(0).toLowerCase() + " + " + materials.get(1).toLowerCase());

                if (newMaterial == null) {
                    newMaterial = combinations.get(materials.get(1).toLowerCase() + " + " + materials.get(0).toLowerCase());
                }
                // mess up with exp saving
                if (newMaterial != null) {
                    System.out.println("New material created: " + newMaterial);
                    gameData.addMaterial(currentUser.getUsername(), newMaterial);
                    gameData.addExp(currentUser.getUsername(), 10);//testing value

                } else {
                    System.out.println("Combination failed. Please try again.");
                }
            }
        }
    }

    //method to read and use data from combinations.txt
    // split lines to materials and new material
    private Map<String, String> readCombinationsFromFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader("combinations.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    combinations.put(parts[0].trim().toLowerCase(), parts[1].trim().toLowerCase());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading combinations file: " + e.getMessage());
        }
        return combinations;
    }

}

