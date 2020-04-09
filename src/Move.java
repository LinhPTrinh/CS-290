import java.util.Scanner;

public abstract class Move {
    protected String input;
    protected Scanner in;
    protected int turn;
    protected String pieceType;

    public Move(){
        in = new Scanner(System.in);
    }

    public void getMove(){
        System.out.println("Player " + turn + ". Enter a move (Z to quit): ");
        input = in.nextLine();
        input = input.toUpperCase();
    }

    public abstract Boolean checkValid();
    public Boolean endGame(){
        if (input.equals("Z"))
            return true;

        return false;
    }
    public String getInput(){
        return input;
    }

    public String getPieceType(){
        return pieceType;
    }

    public void setTurn(int turn){
        this.turn = turn;
        if (turn == 1)
            pieceType = " X ";
        else
            pieceType = " O ";
    }
}
