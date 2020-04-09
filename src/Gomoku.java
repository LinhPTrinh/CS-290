public class Gomoku extends PreviousGames {
    public Gomoku()
    {
        numCol = 19; numRow = 19;
        numPiecetoWin = 5;

        initBoard();
    }

    public boolean placePiece(String location, String pieceType) {
        String column = location.substring(0,1);
        row = 20 - Integer.parseInt(location.substring(1).trim());
        col = column.charAt(0) - 64;

        if (board[row][col].getPieceType().equals(" + ")) {
            board[row][col].setPieceType(pieceType);
            filled = false;
        }
        else {
            System.out.println("Invalid move. Try again.");
            return false;
        }

        return true;
    }
}
