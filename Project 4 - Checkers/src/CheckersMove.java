import java.util.Map;

public class CheckersMove extends Move {
    private Piece[][] board;

    public CheckersMove(Piece[][] board) { this.board = board; }

    public Boolean checkValid() {
        input = input.replaceAll("\\s+", "");

        if ((input.length() != 4 || input.charAt(0) < 65 ||
                input.charAt(0) > 72 || input.charAt(2) < 65 || input.charAt(2) > 72) && input.charAt(0) != 90) {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        else {
            try {
                if (Integer.parseInt(input.substring(1, 2)) > 8) {
                    System.out.println("Invalid move. Try again.");
                    return false;
                }

                if (Integer.parseInt(input.substring(3, 4)) > 8) {
                    System.out.println("Invalid move. Try again.");
                    return false;
                }
            }
            catch (NumberFormatException nfe) {
                System.out.println("Invalid move. Try again.");
                return false;
            }

            return true;
        }
    }

    @Override
    public void findLegalMoves(String pieceType, Board game) {
//        legalMoves.clear(); forceMoves.clear();
        board = game.board;
        Point initPoint = new Point();
        Point endPoint = new Point();


        for (int i = 1; i < game.numRow + 1; i++)
        {
            for (int j = 1; j < game.numCol + 1; j++)
            {
                if (board[i][j].getPieceType().equals(pieceType))
                {
                    String otherType = board[i][j].getOtherPieceType();
                    initPoint.setX(i); initPoint.setY(j);

                    if (board[i][j].getKing()) {
                        endPoint.setX(i-1); endPoint.setY(j-1);
                        addMoves(endPoint, -1, -1, otherType, 0, initPoint);
                        endPoint.setX(i-1); endPoint.setY(j+1);
                        addMoves(endPoint, -1, 1, otherType, 0, initPoint);
                        endPoint.setX(i+1); endPoint.setY(j-1);
                        addMoves(endPoint, 1, -1, otherType, 0, initPoint);
                        endPoint.setX(i+1); endPoint.setY(j+1);
                        addMoves(endPoint, 1, 1, otherType, 0, initPoint);
                    }
                    else if (board[i][j].getPieceType().equals(" X ")) {
                        endPoint.setX(i-1); endPoint.setY(j-1);
                        addMoves(endPoint, -1, -1, otherType, 0, initPoint);
                        endPoint.setX(i-1); endPoint.setY(j+1);
                        addMoves(endPoint, -1, 1, otherType, 0, initPoint);
                    }
                    else {
                        endPoint.setX(i+1); endPoint.setY(j-1);
                        addMoves(endPoint, 1, -1, otherType, 0, initPoint);
                        endPoint.setX(i+1); endPoint.setY(j+1);
                        addMoves(endPoint, 1, 1, otherType, 0, initPoint);
                    }
                }
            }
        }
//
//        for (Map.Entry<Point, Point>
//                entry : legalMoves.entrySet()) {
//            System.out.println("LEGAL MOVES AT THE END");
//            System.out.println(entry.getKey().getX() + " ," + entry.getKey().getY());
//            System.out.println(entry.getValue().getX() + " ," + entry.getValue().getY());
//        }
    }

    private void addMoves(Point endPoint, int rowOffset, int colOffset, String otherType, int count, Point initPoint) {
        if (board[endPoint.getX()][endPoint.getY()].getPieceType().equals("B"))
            return;

        if (board[endPoint.getX()][endPoint.getY()].getPieceType().equals(otherType)){
            count += 1;

            if (count % 2 == 1) {
                endPoint.setX(endPoint.getX() + rowOffset); endPoint.setY(endPoint.getY() + colOffset);
                addMoves(endPoint, rowOffset, colOffset, otherType, count, initPoint);
            }
        }
        else if (board[endPoint.getX()][endPoint.getY()].getPieceType().equals(" + ")) {
            if (count == 0) {
                if (!legalMoves.containsKey(initPoint)) {
                    System.out.println(endPoint.getX() + " ," + endPoint.getY());
                    legalMoves.put(initPoint, endPoint);
                    System.out.println(initPoint.getX() + " ," + initPoint.getY());
                    System.out.println(legalMoves.get(initPoint));
                }
                return;
            }

            if (count % 2 == 1) {
                if (!forceMoves.containsKey(initPoint))
                    forceMoves.put(initPoint, endPoint);

                initPoint.setX(endPoint.getX());
                initPoint.setY(endPoint.getY());
                endPoint.setX(endPoint.getX() + rowOffset); endPoint.setY(endPoint.getY() + colOffset);

                addMoves(endPoint, rowOffset, colOffset, otherType, count + 1, initPoint);
            }

        }
    }
}
