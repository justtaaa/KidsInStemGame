import java.io.IOException;
import java.util.Scanner;

public class LoadGameMenu {
    private UserData userData;
    private GameData gameData;
    public LoadGameMenu (GameData gd, String name) {
        this.gameData = gd;
        this.userData = gd.getUserData(name);
    }

    public void showMenu() throws IOException {

        // creating objects to use in this part of the code
        Scanner scanner = new Scanner(System.in);
        char userChoice;
        do {

            //greetings
            System.out.println("Hello " + userData.getUsername());
            System.out.println("User's level: " + userData.getLevel());

            //method to display options to user
            printOptions();
            userChoice = scanner.next().charAt(0);
            switch (userChoice) {

                //ExplorerMode game
                case 'a':
                    try {
                        System.out.println("Changed to explorer mode.");
                        ExplorerMode explorerMode = new ExplorerMode(userData, gameData);
                        explorerMode.start();
                    } catch (Exception e) {
                        System.err.println("Error starting ExplorerMode: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                // ScienceMode game
                case 'b':
                    System.out.println("Changing to science mode");
                    ScienceMode scienceMode = new ScienceMode(userData, gameData);
                    scienceMode.startQuiz();

                    break;

                // handbook with interesting STEM information for kids
                case 'c':
                    System.out.println("Changing to handbook");
                    Handbook handbook = new Handbook(userData, gameData);
                    handbook.showHandbook();
                    break;

                // change to MainMenu
                case 'x':
                    System.out.println("Changing to the main menu");
                    return;

                //Validation of user's input
                default:
                    System.out.println("Please enter an available option");
                    break;
            }
        } while (userChoice != 'x');
    }
    private void printOptions() {
        System.out.println("Options:");
        System.out.println("a. Explorer");
        System.out.println("b. Science");
        System.out.println("c. Handbook");
        System.out.println("x. Back");
        System.out.print("Enter your choice: ");
    }
}
