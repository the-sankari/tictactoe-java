import java.util.Scanner;

public class Tictactoe {
    // main method
    public static void main(String[] args) {
        // assign the greeting in variable
        String greeting = "Welcome to play Tic-Tac-Toe!\n" + "Type in the Player names.\n";

        // variables for names
        String name1, name2;

        // Array for the game board
        int[][] gameboard = {
                { 0, 0, 0 },
                { 0, 0, 0 },
                { 0, 0, 0 }
        };

        // Player in turn
        int turn;

        // Moves left, in the beginning naturally nine
        int moves = 9;

        // Winning player, tie value 0
        int winner = 0;

        // Object for reading input
        Scanner reader = new Scanner(System.in);

        // Greeting
        System.out.println(greeting);

        // Asking names
        System.out.print("Player 1 name (X): ");
        name1 = reader.nextLine();
        System.out.print("Player 2 name (O): ");
        name2 = reader.nextLine();

        // Randomized who starts first
        turn = randomizeStart();

        // Show empty game board
        showBoard(gameboard);

        do {
            // Ask for move from the player in turn
            System.out.print("Player " + turn + ": ");
            int square = reader.nextInt();
            boolean moveOk = saveMove(gameboard, turn, square);
            if (moveOk) {
                // Move successfull, change turns
                turn = (turn == 1) ? 2 : 1;
                // One less move left
                moves--;

                // Show game situation
                showBoard(gameboard);

                // Check for the winner
                winner = checkWinner(gameboard);

            } else {
                // Move invalid, print report
                System.out.println("Square you choose is not available");

            }

        } while ((moves > 0) && (winner == 0));

        // Congratulate the winner or report a tie
        if (winner != 0) {
            System.out.print("Winner is ");
            System.out.println((winner == 1) ? (name1) : (name2) + "!");
            System.out.println("Congratulations!!!");
        } else {
            System.out.println("Game is a tie");
            System.out.println("Thanks for playing!!!");

        }
    }
}