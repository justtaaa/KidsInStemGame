import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// add exp here as well
public class ScienceMode {
    private Scanner scanner;
    private final UserData currentUser;
    private final GameData gameData;
    public ScienceMode(UserData userData, GameData gameData) throws IOException {
        scanner = new Scanner(System.in);
        this.currentUser = userData;
        this.gameData = gameData;

    }

     // game process >> quiz for kids
    public void startQuiz() {

        // Check if user has enough level to solve the quiz
        if (currentUser.getLevel() < 3) {
            System.out.println("Sorry, you do not have enough level to solve this quiz.");
            return;
        }

        System.out.println("User's level = " + currentUser.getLevel());
        System.out.println("Welcome to the Science Quiz!");
        System.out.println("Answer the following 5 questions to test your knowledge.\n");

        // Read questions from file
        List<Question> questions = readQuestionsFromFile("science.txt");
        System.out.println("Questions read from file: " + questions);

        // Present questions to the user and score their answers
        int userCorrectAnswers = 0;
        int k= 0;
        if (((currentUser.getLevel()) >= 10) && (!gameData.hasItem(currentUser.getUsername(), "Computer"))) {
            k = 10;
        } else if (((currentUser.getLevel()) >= 7) && (!gameData.hasItem(currentUser.getUsername(), "Train"))) {
            k = 5;
        } else if (((currentUser.getLevel()) >= 3) && (!gameData.hasItem(currentUser.getUsername(), "Camera"))) {
            k = 0;
        } else {
            System.out.println("Sorry you already passed the quiz for your level.");
            return;
        }
        for (int i = k; i < k+5; i++) {
            Question question = questions.get(i);
            System.out.println("Question " + (i + 1) + ": " + question.getText());
            System.out.println("Options: ");
            List<String> options = question.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println((char)('a' + j) + ". " + options.get(j));
            }
            System.out.print("Your answer (a, b, c, or d): ");
            String userAnswer = scanner.nextLine();
            System.out.println("User answer: " + userAnswer);
            if (question.isCorrect(userAnswer)) {
                System.out.println("Correct!");
                userCorrectAnswers++;
            } else {
                System.out.println("Incorrect.");
            }
            System.out.println();
        }

        // Determine if user passed the quiz
        if (userCorrectAnswers >= 3) {
            System.out.println("Congratulations, you passed the quiz!");
            gameData.addExp(currentUser.getUsername(), 50);
            String newItem = "";
            if ((currentUser.getLevel()) >= 10) {
                newItem = "Computer";
                gameData.addItem(currentUser.getUsername(), newItem);
            } else if ((currentUser.getLevel()) >= 7) {
                newItem = "Train";
                gameData.addItem(currentUser.getUsername(), newItem);
            } else {
                newItem = "Camera";
                gameData.addItem(currentUser.getUsername(), newItem);
            }
            System.out.println("You earned a new item: " + newItem);
            gameData.addExp(currentUser.getUsername(), 20);

        } else {
            System.out.println("Sorry, you did not pass the quiz.");
            return;
        }
    }

// reading of science.txt file with questions, options and correct answers
    private List<Question> readQuestionsFromFile(String filename) {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String text = parts[0];
                List<String> options = new ArrayList<>();
                for (int i = 1; i <= 4; i++) {
                    options.add(parts[i]);
                }
                String answer = parts[5];
                Question question = new Question(text, options, answer);
                questions.add(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
