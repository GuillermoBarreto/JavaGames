import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] choices = {"Rock", "Paper", "Scissors"};
        String playAgain = "yes";  // Initialize it with "yes" to enter the loop
        
        do {
            // Display the game options to the user
            System.out.println("Rock, Paper, Scissors Game");
            System.out.println("Choose your move:");
            System.out.println("1. Rock");
            System.out.println("2. Paper");
            System.out.println("3. Scissors");
            System.out.print("Enter your choice (1-3): ");
            
            // Get the user's choice
            int userChoice = scanner.nextInt();
            if (userChoice < 1 || userChoice > 3) {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                continue;
            }

            // Get the computer's choice
            Random rand = new Random();
            int computerChoice = rand.nextInt(3) + 1; // Random number between 1 and 3
            System.out.println("Computer's choice: " + choices[computerChoice - 1]);

            // Determine the winner
            if (userChoice == computerChoice) {
                System.out.println("It's a tie!");
            } else if ((userChoice == 1 && computerChoice == 3) || (userChoice == 2 && computerChoice == 1) || (userChoice == 3 && computerChoice == 2)) {
                System.out.println("You win!");
            } else {
                System.out.println("Computer wins!");
            }

            // Ask if the user wants to play again
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next();
        } while (playAgain.equalsIgnoreCase("yes"));
        
        System.out.println("Thanks for playing!");
        scanner.close();
    }
}