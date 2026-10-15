import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * STEP SEM-3 · CodInClub | Powered by BridgeLabz
 * Problem 4: Examination Question Grader
 *
 * Task:
 * An examination system needs to evaluate answers for different types of questions
 * (e.g., Multiple Choice, True/False, Essay). Each question type has a specific grading
 * logic. The system must process a list of questions with provided answers and determine
 * the score for each, then calculate the total score.
 *
 * Business Rules:
 * - MCQ: Full points if StudentAnswer matches CorrectAnswer.
 * - TF: Full points if StudentAnswer matches CorrectAnswer.
 * - ESSAY: Partial points based on comma-separated keywords (case-insensitive):
 *     * At least two keywords found -> 75% of Points.
 *     * Exactly one keyword found -> 50% of Points.
 *     * Otherwise -> 0 points.
 *
 * OOP Design:
 * - Abstract base class `Question` with common fields: `type`, `questionText`,
 *   `correctAnswer`, `studentAnswer`, and `points`.
 * - Abstract method `evaluateScore()`.
 * - Subclasses `McqQuestion`, `TrueFalseQuestion`, and `EssayQuestion` implement their
 *   specialized grading logic polymorphically.
 * - A central grader processes a polymorphic collection (`List<Question>`).
 */
public class ExamQuestionGrader {

    // ==========================================
    // Abstract Base Class
    // ==========================================
    public static abstract class Question {
        private final String type;
        private final String questionText;
        private final String correctAnswer;
        private final String studentAnswer;
        private final double points;

        public Question(String type, String questionText, String correctAnswer, String studentAnswer, double points) {
            this.type = type;
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.studentAnswer = studentAnswer;
            this.points = points;
        }

        public String getType() {
            return type;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public String getStudentAnswer() {
            return studentAnswer;
        }

        public double getPoints() {
            return points;
        }

        /**
         * Evaluates and returns the score awarded for this question.
         */
        public abstract double evaluateScore();
    }

    // ==========================================
    // Concrete Question Subclasses
    // ==========================================

    /**
     * Multiple Choice Question (MCQ):
     * Full points if student answer matches correct answer.
     */
    public static class McqQuestion extends Question {
        public McqQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super("MCQ", questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public double evaluateScore() {
            if (getStudentAnswer() != null && getStudentAnswer().trim().equalsIgnoreCase(getCorrectAnswer().trim())) {
                return getPoints();
            }
            return 0.0;
        }
    }

    /**
     * True/False Question (TF):
     * Full points if student answer matches correct answer.
     */
    public static class TrueFalseQuestion extends Question {
        public TrueFalseQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super("TF", questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public double evaluateScore() {
            if (getStudentAnswer() != null && getStudentAnswer().trim().equalsIgnoreCase(getCorrectAnswer().trim())) {
                return getPoints();
            }
            return 0.0;
        }
    }

    /**
     * Essay Question (ESSAY):
     * Award 75% for >= 2 keywords, 50% for 1 keyword, 0% otherwise.
     */
    public static class EssayQuestion extends Question {
        public EssayQuestion(String questionText, String correctAnswer, String studentAnswer, double points) {
            super("ESSAY", questionText, correctAnswer, studentAnswer, points);
        }

        @Override
        public double evaluateScore() {
            if (getCorrectAnswer() == null || getStudentAnswer() == null) {
                return 0.0;
            }

            String lowerStudentAnswer = getStudentAnswer().toLowerCase(Locale.ROOT);
            String[] keywords = getCorrectAnswer().split(",");

            int keywordCount = 0;
            for (String kw : keywords) {
                String cleanKw = kw.trim().toLowerCase(Locale.ROOT);
                if (!cleanKw.isEmpty() && lowerStudentAnswer.contains(cleanKw)) {
                    keywordCount++;
                }
            }

            if (keywordCount >= 2) {
                return getPoints() * 0.75;
            } else if (keywordCount == 1) {
                return getPoints() * 0.50;
            } else {
                return 0.0;
            }
        }
    }

    // ==========================================
    // Parser for Quoted Question Strings
    // ==========================================
    public static Question parseQuestion(String line) {
        // Regular expression to match quoted tokens or whitespace-delimited tokens
        List<String> tokens = new ArrayList<>();
        Matcher matcher = Pattern.compile("\"([^\"]*)\"|(\\S+)").matcher(line);
        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1));
            } else {
                tokens.add(matcher.group(2));
            }
        }

        if (tokens.size() < 5) {
            throw new IllegalArgumentException("Invalid question input format: " + line);
        }

        String type = tokens.get(0).trim().toUpperCase(Locale.ROOT);
        String questionText = tokens.get(1);
        String correctAnswer = tokens.get(2);
        String studentAnswer = tokens.get(3);
        double points = Double.parseDouble(tokens.get(4).trim());

        switch (type) {
            case "MCQ":
                return new McqQuestion(questionText, correctAnswer, studentAnswer, points);
            case "TF":
                return new TrueFalseQuestion(questionText, correctAnswer, studentAnswer, points);
            case "ESSAY":
                return new EssayQuestion(questionText, correctAnswer, studentAnswer, points);
            default:
                throw new IllegalArgumentException("Unknown question type: " + type);
        }
    }

    // ==========================================
    // Central Grader Processing Logic
    // ==========================================
    public static void gradeQuestions(List<Question> questions) {
        double overallScore = 0.0;
        for (Question question : questions) {
            double score = question.evaluateScore();
            overallScore += score;
            System.out.printf(Locale.US, "%s: %.2f%n", question.getType(), score);
        }
        System.out.printf(Locale.US, "Total Score: %.2f%n", overallScore);
    }

    // ==========================================
    // Main Method (Standard I/O & Demo)
    // ==========================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();

        if (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine().trim();
            while (firstLine.isEmpty() && scanner.hasNextLine()) {
                firstLine = scanner.nextLine().trim();
            }

            if (!firstLine.isEmpty()) {
                try {
                    int n = Integer.parseInt(firstLine);
                    for (int i = 0; i < n && scanner.hasNextLine(); i++) {
                        String line = scanner.nextLine().trim();
                        while (line.isEmpty() && scanner.hasNextLine()) {
                            line = scanner.nextLine().trim();
                        }
                        if (line.isEmpty()) break;
                        questions.add(parseQuestion(line));
                    }
                    gradeQuestions(questions);
                    scanner.close();
                    return;
                } catch (NumberFormatException ignored) {
                    // Fallthrough to demo
                }
            }
        }

        // Demo with Sample Input
        System.out.println("Running default sample test case...");
        questions.add(new McqQuestion("What is the capital of France?", "Paris", "Paris", 10));
        questions.add(new TrueFalseQuestion("The Earth is flat?", "False", "True", 5));
        questions.add(new EssayQuestion("Name two primary OOP principles.", "Inheritance, Polymorphism, Encapsulation", "Polymorphism is one.", 20));
        questions.add(new EssayQuestion("Describe abstraction and composition.", "Abstraction, Composition", "I talked about abstraction.", 15));
        gradeQuestions(questions);
        scanner.close();
    }
}
