import java.util.Scanner;

public class Help {
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.println("Report a bug or type 'x' to go back:");
            userInput = scanner.nextLine();
            if (!userInput.equals("x")) {
                System.out.println("Instructions:");
                System.out.println("Please enter your feedback (maximum 150 characters):");
                String feedback = scanner.nextLine();
                if (feedback.length() > 150) {
                    System.out.println("Your feedback was not sent because it exceeded 150 characters.");
                } else {
                    System.out.println("Thank you for your feedback!");
                }
            }
        } while (!userInput.equals("x"));
    }
}
