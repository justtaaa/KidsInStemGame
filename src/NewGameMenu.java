import java.util.Scanner;

public class NewGameMenu {
    private GameData gameData;
    private UserData userData;

    public NewGameMenu(GameData gd, String name) {
        this.gameData = gd;
        this.userData = gd.getUserData(name);
    }


    public void start() {

        System.out.println("Hello! Welcome to the new game menu " + userData.getUsername());

        // creating objects to use in this part of the code
        Help help = new Help();
        Scanner scanner = new Scanner(System.in);
        char userChoice;

        do {
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
                    }
                    break;

                // user detected a bug
                case 'h':
                    help.showMenu();
                    break;

                    // change to MainMenu
                case 'x':
                    System.out.println("Going back to main menu...");
                    return;

                    //Validation of user's input
                default:
                    System.out.println("Invalid option, please try again.");
            }
        } while (userChoice != 'x');
    }

    private void printOptions() {
        System.out.println("Options:");
        System.out.println("a. Explorer");
        System.out.println("h. Help");
        System.out.println("x. Back to main menu");
    }
}
