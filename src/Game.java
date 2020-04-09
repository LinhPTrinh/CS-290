import java.util.Scanner;

public class Game {
    public static void main(String[] args)
    {
        String game = " ";
        int turn = 1;
        Board gameBoard;
        Move input;
        Scanner in = new Scanner(System.in);
        String playAgain = "Y";

        System.out.println("Welcome to Checkers!");

        while (playAgain.equals("Y"))
        {
            game = printBanner();

            if (game.equals("1")) {
                System.out.println("\nNew Connect 4 game");
                System.out.println("Enter a column to play (ex. D)");
                gameBoard = new Connect4();
                input = new Connect4Move();
            }
            else if (game.equals("2")) {
                System.out.println("\nNew Gomoku game");
                System.out.println("Enter column and row to play (ex. D4)");
                gameBoard = new Gomoku();
                input = new GomokuMove();
            }
            else if (game.equals("3")) {
                System.out.println("\nNew Checkers game");
                System.out.println("To play, first choose a piece. Then enter column and row to move (ex. D4)");
                gameBoard = new Checkers();
                input = new CheckersMove();
            }
            else
                break;

            System.out.println("Player 1 is 'X' and Player 2 is 'O'");
            System.out.println();

            gameBoard.printBoard();
            input.setTurn(turn);
            input.getMove();

            while (!input.endGame())
            {
                while (!input.checkValid() || !gameBoard.placePiece(input.getInput(), input.getPieceType()))
                    input.getMove();

                if (input.endGame())
                    break;

                if (gameBoard.Win(input.getPieceType())) {
                    gameBoard.printBoard();
                    break;
                }

                if (!gameBoard.isFilled())
                {
                    gameBoard.printBoard();

                    if (turn == 1)
                        turn = 2;
                    else
                        turn = 1;
                }

                input.setTurn(turn);
                input.getMove();
            }

            if (input.endGame())
                break;

            System.out.println("Player " + turn + " won!\n");

            System.out.println("Would you like to play again? (Y or N)");
            playAgain = in.nextLine();
            playAgain = playAgain.toUpperCase();

            while (!playAgain.equals("Y") && !playAgain.equals("N"))
            {
                System.out.println("Invalid input. Try again");
                System.out.println("Would you like to play again? (Y or N)");
                playAgain = in.nextLine();
                playAgain = playAgain.toUpperCase();
            }
        }

        System.out.println("Thank you for playing!");
    }

    public static String printBanner(){
        Scanner in = new Scanner(System.in);
        String input;

        System.out.println("\nWhat would you like to play?");
        input = getInput(in);

        while (!input.equals("1") && !input.equals("2") && !input.equals("3") && !input.equals("Z"))
        {
            System.out.println("Invalid input.");
            input = getInput(in);
        }

        return input;
    }

    private static String getInput(Scanner in) {
        String input;
        System.out.println("Press 1 for Connect 4, 2 for Gomoku, and 3 for Checkers.");
        System.out.println("Enter Z to quit.");
        input = in.nextLine();
        input = input.toUpperCase();
        return input;
    }
}


