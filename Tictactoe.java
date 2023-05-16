import java.util.Scanner;

public class Tictactoe {

    // Method for randomized starting the game 

    private static int randomizedStartingPlayer(){
        // Get a number between 1 - 100
        int startNum = (int)(Math.random() * 100 + 1);

        // If number is even, return 1. Ohterwise return 2
        int ret = (startNum % 2 == 0) ? (1) : (2);
        return ret;
    }

    // showBoard method preliminary version
    private static void showBoard(int[][] gameboard){
        int square = 1; // for numbering squares. First square number is 1

        final int cross = character('X');
        final int zero = character('0');

        for (int row = 0; row < gameboard.length; ++row) {
            for (int col = 0; col < gameboard[row].length; ++col) {
                
                // print cross. zero or square number
                if (gameboard [row][col] == cross) {
                    System.out.print('X'); // print X
                } else if(gameboard [row][col] == zero) {
                    System.out.print('0'); // print 0
                }

                System.out.print(square); // print the square number
                
                if (col == gameboard[row].length-1) {
                    // last number of the row printed, print a line change
                    System.out.println();
                    
                } else {
                    // after the first and second number of each row print a | character
                    System.out.println('|');
                    square++; // number of next square is one bigger than the previous
                }

                if(row != gameboard.length - 1)
                System.out.println("-++-");

            }
        }
    }

    private static int character(char c) {
        return 0;
    }

    // saveMove method
    private static boolean saveMove(int[][] gameboard, int pNo, int r){
        int square = 1; // index of the checked element
        final int cross = character('X');
        final int zero = character('0');

        for (int row = 0; row < gameboard.length; ++row) {
            for (int col = 0; col < gameboard[row].length; ++col) {
                // in the square player chose
                if (square == r){
                    if(gameboard[row][col] == cross || gameboard[row][col] == zero){
                        // chosen place already has a cross or zero
                        return false;
                    }else{
                        // no character already ----> place a cross or zero
                        int character = (pNo == 1) ? cross : zero;
                        gameboard[row][col] = character;
                        return true;
                    }
                }
                square++; // move to the next element
            }
        }
        /*if execution reaches this point. saving the character was unsuccessfull and false is returned */
        return false;
    }



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
