import java.util.Scanner;

public class SignIn {
    private String name;
    private String password;
    private GameData gameData;

    public SignIn(GameData gd) {
        this.gameData = gd;
    }

    public String signIn() {
        Scanner scanner = new Scanner(System.in);
        boolean isSignedIn = false;

        // Loop until the user successfully signs in
        while (!isSignedIn) {
            // Prompt the user to enter their name
            System.out.println("Please enter your name (letters only):");
            while (true) {
                name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    System.out.println("Name cannot be empty. Please try again:");
                    continue;
                }
                if (!name.matches("^[a-zA-Z]+$")) {
                    System.out.println("Invalid name. Please try again:");
                    continue;
                }
                break;
            }

            // Prompt the user to enter their password
            System.out.println("Please enter a password (4 numbers only):");
            while (true) {
                password = scanner.nextLine().trim();
                if (password.isEmpty()) {
                    System.out.println("Password cannot be empty. Please try again:");
                    continue;
                }
                break;
            }

            // Check the password in the GameData object to match the name
            boolean isPasswordCorrect = gameData.checkPassword(name, password);
            if (isPasswordCorrect) {
                isSignedIn = true;
                System.out.println("Successfully signed in!");
            } else {
                // Handle invalid login credentials
                System.out.println("Invalid user or password. Please try again.");
                System.out.println("Enter 1 to try again, or 2 to go back to the main menu.");
                String choice = scanner.nextLine();
                if (choice.equals("2")) {
                    // User chooses to go back to the main menu
                    return null;
                }
            }
        }

        // Return the user's name on successful sign-in
        return name;
    }
}
