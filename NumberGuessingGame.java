import java.util.*;

public class NumberGuessingGame {
    private int secretNumber;
    private int maxAttempts;
    private int score;
    private int rounds;
    private Scanner scanner;

    public NumberGuessingGame() {
        maxAttempts = 3;
        score = 0;
        rounds = 0;
        scanner = new Scanner(System.in);
    }

    private void generateSecretNumber() {
        secretNumber = new Random().nextInt(100) + 1;
    }

    private boolean isValidAttempt(int attempt) {
        return attempt >= 1 && attempt <= maxAttempts;
    }

    private void displayScores() {
        System.out.println("\nScores:");
        System.out.println("Rounds played: " + rounds);
        System.out.println("Total Score: " + score);
        System.out.println("Average Score per round: " + (rounds > 0 ? (double) score / rounds : 0));
    }

    private int calculateScore(int attempts) {
        return (maxAttempts - attempts + 1) * 10;
    }

    public void playGame() {
        String playAgain;
        do {
            generateSecretNumber();
            rounds++;

            System.out.println("\nRound " + rounds + ": Guess the number between 1 and 100.");
            int attempts = 0;
            int attempt;
            boolean isCorrect = false;

            while (!isCorrect && attempts < maxAttempts) {
                attempts++;
                System.out.print("Attempt " + attempts + ": Enter your guess: ");
                attempt = Integer.parseInt(scanner.nextLine());

                if (attempt == secretNumber) {
                    score += calculateScore(attempts);
                    isCorrect = true;
                    System.out.println("Congratulations! You guessed the correct number.");
                } else if (attempt < secretNumber) {
                    System.out.println("Try a higher number.");
                } else {
                    System.out.println("Try a lower number.");
                }
            }

            if (!isCorrect) {
                System.out.println("Out of attempts. The secret number was: " + secretNumber);
            }

            System.out.println("Your Score for this round: " + calculateScore(attempts));

            System.out.print("Do you want to play again? (y/n): ");
            playAgain = scanner.nextLine();
        } while (playAgain.equalsIgnoreCase("y"));

        displayScores();
        System.out.println("Thanks for playing!");
    }

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.playGame();
    }
}
