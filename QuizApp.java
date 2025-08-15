import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctAnswer; // index of correct option (0-3)

    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s%n", i + 1, options[i]);
        }
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer - 1 == correctAnswer;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        // Add questions
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"Berlin", "Madrid", "Paris", "Rome"},
                2
        ));

        questions.add(new Question(
                "Which language is used for Android development?",
                new String[]{"Swift", "Java", "Python", "C++"},
                1
        ));

        questions.add(new Question(
                "Who wrote 'Romeo and Juliet'?",
                new String[]{"William Wordsworth", "William Shakespeare", "Charles Dickens", "Mark Twain"},
                1
        ));

        questions.add(new Question(
                "What is the result of 3 * 4?",
                new String[]{"7", "12", "9", "14"},
                1
        ));

        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"Earth", "Venus", "Mars", "Jupiter"},
                2
        ));

        int score = 0;

        System.out.println("=== Welcome to the Online Quiz ===\n");

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.printf("Question %d:%n", i + 1);
            q.displayQuestion();

            int answer = 0;
            while (true) {
                System.out.print("Your answer (1-4): ");
                if (scanner.hasNextInt()) {
                    answer = scanner.nextInt();
                    if (answer >= 1 && answer <= 4) break;
                } else {
                    scanner.next(); // discard invalid input
                }
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }

            if (q.isCorrect(answer)) {
                System.out.println("✅ Correct!\n");
                score++;
            } else {
                System.out.println("❌ Incorrect. The correct answer was: " + (q.correctAnswer + 1) + ". " + q.options[q.correctAnswer] + "\n");
            }
        }

        // Show results
        System.out.println("=== Quiz Completed ===");
        System.out.printf("Your score: %d out of %d%n", score, questions.size());

        if (score == questions.size()) {
            System.out.println("🎉 Perfect score! Well done!");
        } else if (score >= questions.size() / 2) {
            System.out.println("👍 Good job! You passed.");
        } else {
            System.out.println("📘 Keep practicing. You'll get better!");
        }
    }
}
