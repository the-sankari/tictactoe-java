import java.util.Scanner;

public class Tictactoe {

    // Method for randomized starting the game

    private static int randomizedStartingPlayer() {
        // Get a number between 1 - 100
        int startNum = (int) (Math.random() * 100 + 1);

        // If number is even, return 1. Ohterwise return 2
        int ret = (startNum % 2 == 0) ? (1) : (2);
        return ret;
    }

    // showBoard method
    private static void showBoard(int[][] gameBoard) {
        int square = 1; // for numbering squares. First square number is 1

        char cross = 'X';
        char zero = '0';

        for (int row = 0; row < gameBoard.length; ++row) {
            for (int col = 0; col < gameBoard[row].length; ++col) {

                // print cross, zero, or square number
                if (gameBoard[row][col] == character(cross)) {
                    System.out.print(cross); // print X
                } else if (gameBoard[row][col] == character(zero)) {
                    System.out.print(zero); // print 0
                } else {
                    System.out.print(square); // print the square number
                }

                if (col != gameBoard[row].length - 1) {
                    System.out.print('|'); // print the vertical separator
                }

                square++; // increment square number
            }

            System.out.println(); // print a new line after each row

            if (row != gameBoard.length - 1) {
                System.out.println("---+---"); // print horizontal separator line
            }
        }
    }

    private static int character(char m) {
        if (m == 'X') {
            return 1;
        } else if (m == '0') {
            return 2;
        } else {

            return 0;
        }
    }

    // saveMove method
    private static boolean saveMove(int[][] gameBoard, int pNo, int r) {
        int square = 1; // index of the checked element
        final int cross = character('X');
        final int zero = character('0');

        for (int row = 0; row < gameBoard.length; ++row) {
            for (int col = 0; col < gameBoard[row].length; ++col) {
                // in the square player chose
                if (square == r) {
                    if (gameBoard[row][col] == cross || gameBoard[row][col] == zero) {
                        // chosen place already has a cross or zero
                        return false;
                    } else {
                        // no character already ----> place a cross or zero
                        int character = (pNo == 1) ? cross : zero;
                        gameBoard[row][col] = character;
                        return true;
                    }
                }
                square++; // move to the next element
            }
        }
        /*
         * if execution reaches this point. saving the character was unsuccessfull and
         * false is returned
         */
        return false;
    }

    private static int checkWinner(int[][] gameBoard) {
        // calculate the squares of numbers presenting cross and zero
        final int threeCrosses = character('X') * character('X') * character('X');
        final int threeZeros = character('0') * character('0') * character('0');

        // Check horizontal lines, row multiplication
        for (int row = 0; row < gameBoard.length; ++row) {
            int multi = gameBoard[row][0] * gameBoard[row][1] * gameBoard[row][2];

            // Three crosses in a line?
            if (multi == threeCrosses)
                return 1; // player 1 won

            // Three zeros in a line
            if (multi == threeZeros)
                return 2; // player 2 won
        }

        // Check for vertical line, cloumn multiplication
        for (int col = 0; col < gameBoard.length; ++col) {
            int multi = gameBoard[0][col] * gameBoard[1][col] * gameBoard[2][col];

            // three crosses in a line?
            if (multi == threeCrosses)
                return 1; // player 1 won
            if (multi == threeZeros)
                return 2; // player 2 won
        }

        // finally check for diagonal lines

        // from top left to bottom right
        int tlbr = gameBoard[0][0] * gameBoard[1][1] * gameBoard[2][2];

        // from bottom left to top right
        int bltr = gameBoard[2][0] * gameBoard[1][1] * gameBoard[0][2];

        // three crosses in a line
        if (tlbr == threeCrosses || bltr == threeCrosses)
            return 1; // player 1 won

        // three zeros in a line
        if (tlbr == threeZeros || bltr == threeZeros)
            return 2; // player 2 won

        // If execution reaches this point, winner is not known
        return 0;

    }

    // main method
    public static void main(String[] args) {
        // assign the greeting in variable
        String greeting = "Welcome to play Tic-Tac-Toe!\n" + "Type in the Player names.\n";

        // variables for names
        String name1, name2;

        // Array for the game board
        int[][] gameBoard = {
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
        turn = randomizedStartingPlayer();

        // Show empty game board
        showBoard(gameBoard);

        do {
            // Ask for move from the player in turn
            System.out.print("Player " + turn + ": ");
            int square = reader.nextInt();
            boolean moveOk = saveMove(gameBoard, turn, square);
            if (moveOk) {
                // Move successfull, change turns
                turn = (turn == 1) ? 2 : 1;
                // One less move left
                moves--;

                // Show game situation
                showBoard(gameBoard);

                // Check for the winner
                winner = checkWinner(gameBoard);

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
