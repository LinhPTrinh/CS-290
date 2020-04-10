import java.util.Scanner;
import java.util.TreeMap;

public abstract class Move {
    protected String input;
    protected Scanner in;
    protected int turn;
    protected String pieceType;
//    protected TreeMap<Point, Point> legalMoves;
//    protected TreeMap<Point, Point> forceMoves;

    public Move(){
        in = new Scanner(System.in);
//        legalMoves = new TreeMap<>();
//        forceMoves = new TreeMap<>();
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

//    public TreeMap<Point, Point> getLegalMoves() {
//        return legalMoves;
//    }
//
//    public TreeMap<Point, Point> getForceMoves() {
//        return forceMoves;
//    }

    public abstract void findLegalMoves(String pieceType, Board game);

}
