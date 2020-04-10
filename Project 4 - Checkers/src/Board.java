import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.TreeMap;

public abstract class Board {
    protected Piece[][] board;
    protected boolean filled;
    protected int numCol;
    protected int numRow;
    protected boolean win;
    protected int row;
    protected int col;
//    protected TreeMap <Point,Point> legalMoves;
//    protected TreeMap <Point,Point> forceMoves;

    public void initBoard(int game){
        int i, j;
        board = new Piece[numRow+2][numCol+2];

        for (i = 0; i < numRow+2; i++)
        {
            for (j = 0; j < numCol+2; j++)
            {
                if (game == 3)
                    board[i][j] = new CheckersPiece();
                else
                    board[i][j] = new Piece();

                // Set border
                if (i == 0 || j == 0)
                    board[i][j].setPieceType("B");
                else if (i == numRow+1 || j == numCol+1)
                    board[i][j].setPieceType("B");
            }
        }

//        legalMoves = new TreeMap<>();
//        forceMoves = new TreeMap<>();
    }

    public void printBoard() {
        int i = 0, j = 0;

        int rowLabel = numRow;
        char columnLabel = 65;
        for (i = 1; i < numRow + 1; i++)
        {
            if (rowLabel < 10)
                System.out.print(" ");

            System.out.print(rowLabel);

            for (j = 1; j < numCol + 1; j++)
            {
                System.out.print(board[i][j].getPieceType());
            }

            System.out.print("\n");
            rowLabel--;
        }

        System.out.print("  ");

        for (i = 0; i < numRow; i++)
        {
            System.out.print(String.valueOf(" " + columnLabel + " "));
            columnLabel++;
        }
        System.out.print("\n\n");
    }
    public abstract boolean placePiece(String location, String pieceType);

    public boolean isFilled(){
        return filled;
    }

    public abstract boolean Win(String pieceType);

//    public void setLegalMoves (TreeMap<Point,Point> legalMoves) {
//        for (Map.Entry<Point, Point>
//                entry : legalMoves.entrySet()) {
//            this.legalMoves.put(entry.getKey(), entry.getValue());
//        }
//    }
//
//    public void setForceMoves (TreeMap<Point, Point> forceMoves) {
//        for (Map.Entry<Point, Point>
//                entry : forceMoves.entrySet()) {
//            this.forceMoves.put(entry.getKey(), entry.getValue());
//        }
//    }
}
