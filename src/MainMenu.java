import java.io.IOException; //library to catch possible exceptions
import java.util.Scanner; // library to use Scanner Class, get user's input

public class MainMenu {

    public void start() throws IOException {

        // creating objects to use through the code
        Scanner scanner = new Scanner(System.in);
        Help help = new Help();
        GameData gameData = new GameData();
        gameData.readFromFile("users.txt");

        // preventing infinitive loop
        int attempts = 0;

        while (true) {

            //method to display options to user
            printOptions();
            String name;

            char userChoice = scanner.next().charAt(0);
            switch (userChoice) {

                //New Game >> user needs signUp
                case 'a':
                    SignUp signUp = new SignUp(gameData);
                    name = signUp.signUp();

                    //signUp is successful >> new object to execute New Game Menu
                    NewGameMenu newGameMenu = new NewGameMenu(gameData, name);
                    newGameMenu.start();
                    break;

                    //Load Game >> user has account, needs signIn
                case 'b':
                    SignIn signIn = new SignIn(gameData);
                    name = signIn.signIn();

                    //signIn is successful >> new object to executeLoad Gam eMenu
                    LoadGameMenu loadGameMenu = new LoadGameMenu(gameData, name);
                    loadGameMenu.showMenu();
                    break;

                    // user detected a bug
                case 'c':
                    help.showMenu();
                    break;

                    // End the game
                    // Stop running the game
                case 'x':
                    System.out.println("Exiting game...");
                    return;

                    //validation to detect the correctness of user's input
                default:
                    System.out.println("Invalid option, please try again.");
                    attempts++;
            }
            if (attempts >= 3) {
                System.out.println("Too many invalid attempts, exiting game...");
                return;
            }
        }


    }
    private void printOptions() {
        System.out.println("Intelligence");
        System.out.println("Choose an option:");
        System.out.println("a. New game");
        System.out.println("b. Load game");
        System.out.println("c. Help");
        System.out.println("x. Exit");

    }
}