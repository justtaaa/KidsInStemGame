import java.io.*;
import java.util.*;

public class GameData {
    private Map<String, UserData> users;

    public UserData getUserData(String username){
        return  users.get(username);

    }

    public void newUser(String name, UserData userData){
        users.put(name, userData);
        writeToFile();
    }

    // check password in SignIn
    public boolean checkPassword(String username, String password) {
        readFromFile("users.txt");
        if (users.containsKey(username)) {
            UserData user = users.get(username);
            return user.getPassword().equals(password);
        }
        return false;
    }


    public GameData() {
        this.users = new HashMap<>();
    }

    //write data about user to users.txt: name, password, exp, level, all materials and all items
    public void writeToFile() {
        try {
            System.out.println("Writing user data to file: " + "users.txt");
            FileWriter writer = new FileWriter("users.txt", false);
            for (Map.Entry<String, UserData> entry : users.entrySet()) {
                UserData user = entry.getValue();
                writer.write("user = " + user.getUsername() + "; ");
                writer.write("password = " + user.getPassword() + "; ");
                writer.write("exp = " + user.getExp() + "; ");
                writer.write("level = " + user.getLevel() + "; ");
                writer.write("materials =");
                boolean first = true;
                for (String s : user.getMaterials()) {
                    if (first)
                        first = false;
                    else
                        writer.write(",");
                    writer.write(s);
                }
                writer.write("; ");
                writer.write("items =");
                boolean first1 = true;
                for (String s1 : user.getItems()) {
                    if (first1)
                        first1 = false;
                    else
                        writer.write(",");
                    writer.write(s1);
                }
                writer.write("\n");
            }
            writer.close();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Exception");
            throw new RuntimeException(e);
        }
    }


    // add NewMaterial in ExplorerMode
    public void addMaterial(String currentUser, String newMaterial) {
        UserData userData = users.get(currentUser);
        if (userData != null) {
            userData.addMaterial(newMaterial);
            System.out.println("Adding material " + newMaterial + " for user " + currentUser);
            writeToFile();
        }
    }

    // checking if user has material he/she wants to combine in ExplorerMode
    public boolean hasMaterial(String currentUser, String material) {
        UserData userData = users.get(currentUser);
        if (userData == null) {
            System.out.println("User " + currentUser + " not found!");
            return false;
        }
        System.out.println("User " + currentUser + " inventory: " + userData.getMaterials());
        boolean hasMaterial = userData.hasMaterial(material);
        System.out.println("User " + currentUser + " has material " + material + "? " + hasMaterial);
        return hasMaterial;
    }



    // to display user's materials from the file users.txt and display them in  ExplorerMode
    public List<String> getMaterials(String currentUser) {
        UserData userData = users.get(currentUser);
        List<String> materials = new ArrayList<>();
        if (userData != null) {
            for (String material : userData.getMaterials()) {
                String materialWithoutSpaces = material.replaceAll("\\s+","");
                materials.add(materialWithoutSpaces);
            }
        }
        return materials;
    }

    // to display user's items from the file users.txt and display them in  Handbook
    public List<String> getItems(String currentUser) {
        UserData userData = users.get(currentUser);
        List<String> items = new ArrayList<>();
        if (userData != null) {
            for (String item : userData.getItems()) {
                String itemWithoutSpaces = item.replaceAll("\\s+","");
                items.add(itemWithoutSpaces);
            }
        }
        return items;
    }

    // checking if user has item he/she wants craft in ScienceMode
    public boolean hasItem(String currentUser, String item) {
        UserData userData = users.get(currentUser);
        if (userData == null) {
            System.out.println("User " + currentUser + " not found!");
            return false;
        }
        System.out.println("User " + currentUser + " inventory: " + userData.getItems());
        boolean hasItem = userData.hasItem(item);
        System.out.println("User " + currentUser + " has item " + item + "? " + hasItem);
        return hasItem;
    }

    // add NewMaterial in ScienceMode
    public void addItem(String currentUser, String newItem) {
        UserData userData = users.get(currentUser);
        if (userData != null) {
            userData.addItem(newItem);
            System.out.println("Adding item " + newItem + " for user " + currentUser);
            writeToFile();
        }
    }

    public void addExp(String currentUser, int exp) {
        UserData userData = users.get(currentUser);
        if (userData != null) {
            userData.addExp(exp);
            writeToFile();
        }
    }

    // reads users from file users.txt in a specific format
    public void readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(";");
                String username = null, password = null;
                int level = 0, exp = 0;
                String[] tempItems = null, tempMaterials = null;
                List<String> materials = new ArrayList<>();
                List<String> items = new ArrayList<>();
                for (String field : fields) {
                    String[] keyValue = field.split("=");
                    switch (keyValue[0].trim()) {
                        case "user":
                            username = keyValue[1].trim();
                            break;
                        case "password":
                            password = keyValue[1].trim();
                            break;
                        case "level":
                            level = Integer.parseInt(keyValue[1].trim());
                            break;
                        case "exp":
                            exp = Integer.parseInt(keyValue[1].trim());
                            break;
                        case "materials":
                            tempMaterials = keyValue[1].split(",");
                            for (String material : tempMaterials){
                                 material.trim();
                                 materials.add(material);
                            }
                            break;
                        case "items":
                            tempItems = keyValue[1].split(",");
                        for (String item : tempItems){
                            item.trim();
                            items.add(item);
                        }
                        break;
                    }
                }
                if (username != null && password != null) {
                    UserData user = new UserData(username, password, level, exp, materials, items);
                    users.put(username, user);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }


}


