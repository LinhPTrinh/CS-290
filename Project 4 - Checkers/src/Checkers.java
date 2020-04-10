import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

public class Checkers extends Board{
    public Checkers()
    {
        numCol = 8; numRow = 8;
        initBoard(3);
        String pieceType = " O ";
        int i, j;

        for (i = 1; i < numRow + 1; i++) {
            if (i == 4) {
                i += 2;
                pieceType = " X ";
            }

            if (i % 2 == 0) {
                for (j = 1; j < numCol + 1; j = j + 2)
                    setPiece(pieceType, i, j);
            } else
                for (j = 0; j < numCol + 1; j = j + 2)
                    setPiece(pieceType, i, j);
        }
//            for (j = 1; j < numCol + 1; j++) {
//                if (i >= 4 && i < 6)
//                    setPiece(" + ", i, j);
//                else {
//                    if (i >= 6)
//                        pieceType = " X ";
//
//                    if (i%2 == 0 && j%2 == 1)
//                        setPiece(pieceType, i, j);
//                    else if (i%2 == 1 && j%2 == 0)
//                        setPiece(pieceType, i, j);
//                    else
//                        setPiece(" + ", i, j);
//                }


    }

    public boolean placePiece(String location, String pieceType) {
        boolean valid;

        location = location.replaceAll("\\s+", "");

        String oldLocation = location.substring(0, 2);
        String newLocation = location.substring(2, 4);

        valid = implementMove(oldLocation, newLocation, pieceType);

        return valid;
    }

    public boolean implementMove(String oldLocation, String newLocation, String pieceType) {
        int newRow, newCol, rowOffset, colOffset;
        Point endPoint = new Point();
        Point initPoint = new Point();
        Point currentLocation = new Point();

        setRowCol(newLocation);
        endPoint = setPoint(row, col);
        setRowCol(oldLocation);
        initPoint = setPoint(row, col);

        if (checkString(pieceType))
        {
            if (!forceMoves.isEmpty() &&
                         !forceMoves.containsKey(initPoint)) {
                System.out.println("You need to make a capturing move");
                return false;
            }
            else if (forceMoves.containsKey(initPoint) && forceMoves.get(initPoint).equals(endPoint))
            {
                setPiece(" + ", initPoint.getX(), initPoint.getY());

                if (board[initPoint.getX()][initPoint.getY()].getKing()) {
                    board[initPoint.getX()][initPoint.getY()].setKing(false);
                    board[endPoint.getX()][endPoint.getY()].setKing(true);
                    board[endPoint.getX()][endPoint.getY()].setPieceType(pieceType);
                }

                rowOffset = (initPoint.getX() - endPoint.getX())/2;
                colOffset = (initPoint.getY() - endPoint.getY())/2;
                setPiece(" + ", row - rowOffset, col - colOffset);

                setEndPoint(endPoint, pieceType);
            }
            else if (legalMoves.containsKey(initPoint) && legalMoves.get(initPoint).equals(endPoint))
            {
                setPiece(" + ", initPoint.getY(), initPoint.getY());
                setEndPoint(endPoint, pieceType);
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


//        if (pieceType.equals(board[row][col].getPieceType()))
//        {
//            setRowCol(newLocation);
//            currentLocation.x = row; currentLocation.y = col;
//
//            if (!forceMoves.isEmpty() && !forceMoves.contains(currentLocation)) {
//                System.out.println("You need to make a capturing move");
//                return false;
//            }
//
//            if (forceMoves.contains(currentLocation)) {
//                setPiece(pieceType, row, col);
//                newRow = col; newCol = col;
//
//                setRowCol(oldLocation);
//                setPiece(" + ", row, col);
//
//                if (board[row][col].getKing()) {
//                    board[row][col].setKing(false);
//                    board[newRow][newCol].setKing(true);
//                }
//
//                rowOffset = (row - newRow)/2;
//                colOffset = (col - newCol)/2;
//                System.out.println(row - rowOffset);
//                System.out.println(col - colOffset);
//                setPiece(" + ", row - rowOffset, col - colOffset);
//
//                if (pieceType.equals(" X ") && newRow == 1)
//                    board[newRow][newCol].setKing(true);
//                else if (pieceType.equals(" O ") && newRow == 8)
//                    board[newRow][newCol].setKing(true);
//            }
//            else if (legalMoves.contains(currentLocation)) {
//                setPiece(pieceType, row, col);
//                setRowCol(oldLocation);
//                setPiece(" + ", row, col);
//            }
//            else {
//                System.out.println("You cannot move there. Try again.");
//                return false;
//            }
//        }
//        else {
//            System.out.println("That is not your piece. Try again");
//            return false;
//        }

        return true;
    }

    private void setPiece(String pieceType, int row, int col) {
        board[row][col].setPieceType(pieceType);
    }
//
    public void setRowCol (String location) {
        String column = location.substring(0,1);
        row = (numRow + 1) - Integer.parseInt(location.substring(1,2));
        col = column.charAt(0) - 64;
    }

    public Point setPoint(int row, int col) {
        Point newPoint = new Point();
        newPoint.setX(row); newPoint.setY(col);
        return newPoint;
    }

    public boolean checkString(String pieceType) {
        if (pieceType.equals(board[row][col].getPieceType()))
            return true;

        if (pieceType.equals(" X ") && board[row][col].getPieceType().equals("xKx"))
            return true;

        if (pieceType.equals(" O ") && board[row][col].getPieceType().equals("oKo"))
            return true;

        return false;

    }

    public void setEndPoint(Point endPoint, String pieceType) {
        if (pieceType.equals(" X ") && endPoint.getX() == 1) {
            board[endPoint.getX()][endPoint.getY()].setKing(true);
            board[endPoint.getX()][endPoint.getY()].setPieceType(pieceType);
        }
        else if (pieceType.equals(" O ") && endPoint.getX() == 8) {
            board[endPoint.getX()][endPoint.getY()].setKing(true);
            board[endPoint.getX()][endPoint.getY()].setPieceType(pieceType);
        }
        else
            setPiece(pieceType, endPoint.getX(), endPoint.getY());
    }

    public boolean Win(String pieceType){ return false  ;}
}
