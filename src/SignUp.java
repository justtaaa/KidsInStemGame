import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SignUp {

    private String name;
    private String password;
    List<String> materials = new ArrayList<String>(Arrays.asList("water", "air", "earth", "fire"));
    List<String> items = new ArrayList<String>(Arrays.asList("handbook"));
    private GameData gameData;

    public SignUp(GameData gd) {
        this.gameData = gd;
    }

    public String signUp() {
        Scanner scanner = new Scanner(System.in);

        // Loop until a unique username is entered
        while (true) {
            System.out.println("Please enter your name (letters only):");
            name = scanner.nextLine();

            // Check if the username already exists
            if (gameData.getUserData(name) != null) {
                System.out.println("That username is already taken. Please choose a different one.");
            } else if (!isValidName(name)) {
                System.out.println("Invalid username. Please enter letters only.");
            } else {
                break;
            }
        }

        System.out.println("Please enter a password (4 numbers only):");
        password = scanner.nextLine();


        while (!isValidPassword(password)) {
            System.out.println("Invalid password. Please enter 4 numbers only:");
            password = scanner.nextLine();
        }

        // creating userData object to create, save and use all information about the user in the future
        UserData userData = new UserData(name, password, 1, 0, materials, items);
        gameData.newUser(name, userData);
        return name;
    }

    // Validation of username input
    private boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    // Validation of password input
    private boolean isValidPassword(String password) {
        if (password.length() != 4) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}

