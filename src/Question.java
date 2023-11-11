import java.util.List;

public class Question {
    private final String text;
    private final List<String> options;
    private final String answer;

    public Question(String text, List<String> options, String answer) {
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect(String userAnswer) {
        return answer.equalsIgnoreCase(userAnswer);
    }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                '}';
    }
}
