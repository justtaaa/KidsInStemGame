import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Handbook {

    private final UserData currentUser;
    private final GameData gameData;
   public Handbook(UserData userData,  GameData gameData) throws IOException {
       this.currentUser = userData;
       this.gameData = gameData;

   }


    public void showHandbook(){
        System.out.println("Available items = " + gameData.getItems(currentUser.getUsername()));
        for(String s : gameData.getItems(currentUser.getUsername()))  {
            displayItem(s);
        }
    }


    // getting the item and displays a page (text) with interesting information about it
    public void displayItem(String itemName) {
        try (BufferedReader br = new BufferedReader(new FileReader("handbook.txt"))) {
            String line;
            boolean itemFound = false;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("Level ")) {
                    String currentItem = line.substring(6);
                    if (currentItem.equalsIgnoreCase(itemName)) {
                        itemFound = true;
                    }
                    if (itemFound && (!currentItem.equalsIgnoreCase(itemName))){
                        break;
                    }
                }
                if (itemFound) {
                    System.out.println(line);
                }
            }
            if (!itemFound) {
                System.out.println("Item not found in handbook.");
            }
        } catch (IOException e) {
            System.out.println("Error reading handbook file.");
        }
    }
}