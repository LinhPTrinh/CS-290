import java.awt.*;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Checkers extends Board{
    private Vector<Point> legalMoves;
    private Vector<Point> forceMoves;

    public Checkers()
    {
        numCol = 8; numRow = 8;
        initBoard();
        String pieceType = " O ";

        for (int i = 1; i < numRow + 1; i++) {
            if (i == 4) {
                i += 2;
                pieceType = " X ";
            }

            if (i%2 == 0) {
                for (int j = 1; j < numCol + 1; j = j+2)
                    setPiece(pieceType, i, j);
            }
            else {
                for (int j = 2; j < numCol + 1; j = j+2)
                    setPiece(pieceType, i, j);
            }
        }

        legalMoves = new Vector<>();
        forceMoves = new Vector<>();
    }

    public boolean placePiece(String location, String pieceType) {
        boolean valid;

        getMoves(pieceType);
        location = location.replaceAll("\\s+", "");

        String oldLocation = location.substring(0, 2);
        String newLocation = location.substring(2, 4);

        valid = implementMove(oldLocation, newLocation, pieceType);

        return valid;
    }

    public boolean implementMove(String oldLocation, String newLocation, String pieceType) {
        int newRow, newCol, rowOffset, colOffset;
        Point currentLocation = new Point();
        setRowCol(oldLocation);

        if (pieceType.equals(board[row][col].getPieceType()))
        {
            setRowCol(newLocation);
            currentLocation.x = row; currentLocation.y = col;

            if (!forceMoves.isEmpty() && !forceMoves.contains(currentLocation)) {
                System.out.println("You need to make a capturing move");
                return false;
            }

            if (forceMoves.contains(currentLocation)) {
                setPiece(pieceType, row, col);
                newRow = col; newCol = col;

                setRowCol(oldLocation);
                setPiece(" + ", row, col);

                if (board[row][col].getKing()) {
                    board[row][col].setKing(false);
                    board[newRow][newCol].setKing(true);
                }

                rowOffset = (row - newRow)/2;
                colOffset = (col - newCol)/2;
                System.out.println(row - rowOffset);
                System.out.println(col - colOffset);
                setPiece(" + ", row - rowOffset, col - colOffset);

                if (pieceType.equals(" X ") && newRow == 1)
                    board[newRow][newCol].setKing(true);
                else if (pieceType.equals(" O ") && newRow == 8)
                    board[newRow][newCol].setKing(true);
            }
            else if (legalMoves.contains(currentLocation)) {
                setPiece(pieceType, row, col);
                setRowCol(oldLocation);
                setPiece(" + ", row, col);
            }
            else {
                System.out.println("You cannot move there. Try again.");
                return false;
            }
        }
        else {
            System.out.println("That is not your piece. Try again");
            return false;
        }

        return true;
    }

    public void getMoves(String pieceType) {
        legalMoves.clear(); forceMoves.clear();

        for (int i = 1; i < numRow + 1; i++)
        {
            for (int j = 1; j < numCol + 1; j++)
            {
                if (board[i][j].getPieceType().equals(pieceType))
                {
                    String otherType = board[i][j].getOtherPieceType();
                    if (board[i][j].getKing()) {
                        addMoves(i - 1, j - 1, -1, -1, pieceType, otherType, 0);
                        addMoves(i - 1, j + 1, -1, 1, pieceType, otherType, 0);
                        addMoves(i + 1, j - 1, 1, -1, pieceType, otherType, 0);
                        addMoves(i + 1, j + 1, 1, 1, pieceType, otherType, 0);
                    }
                    else if (board[i][j].getPieceType().equals(" X ")) {
                        addMoves(i - 1, j - 1, -1, -1, pieceType, otherType, 0);
                        addMoves(i - 1, j + 1, -1, 1, pieceType, otherType, 0);
                    }
                    else {
                        addMoves(i + 1, j - 1, 1, -1, pieceType, otherType, 0);
                        addMoves(i + 1, j + 1, 1, 1, pieceType, otherType, 0);
                    }
                }
            }
        }
    }

    private void addMoves(int row, int col, int rowOffset, int colOffset, String pieceType, String otherType, int count)
    {
        if (board[row][col].getPieceType().equals("B"))
            return;

        if (board[row][col].getPieceType().equals(otherType)){
            count += 1;

            if (count % 2 == 1)
                addMoves(row + rowOffset,
                        col + colOffset, rowOffset, colOffset, pieceType, otherType, count);
        }
        else if (board[row][col].getPieceType().equals(" + ")) {
            if (count == 0) {
                Point pointEntry = new Point(row, col);

                if (!legalMoves.contains(pointEntry))
                    legalMoves.addElement(pointEntry);
                return;
            }

            if (count % 2 == 1) {
                Point pointEntry = new Point(row, col);

                if (!forceMoves.contains(pointEntry))
                    forceMoves.addElement(pointEntry);

                addMoves(row + rowOffset,
                        col + colOffset, rowOffset, colOffset, pieceType, otherType, count + 1);
            }

        }
    }

    private void setPiece(String pieceType, int row, int col) {
        board[row][col].setPieceType(pieceType);
    }

    public void setRowCol (String location) {
        String column = location.substring(0,1);
        row = (numRow + 1) - Integer.parseInt(location.substring(1,2));
        col = column.charAt(0) - 64;
    }

    public boolean Win(String pieceType){ return false  ;}
}
