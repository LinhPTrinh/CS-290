public class Connect4 extends PreviousGames {
    public Connect4(){
        numRow = 6; numCol = 7;
        numPiecetoWin = 4;

        initBoard();
    }

    public void printBoard() {
        int i = 0, j = 0;
        for (i = 1 ; i < numRow+1; i++)
        {
            for (j = 1; j < numCol+1; j++)
                System.out.print(board[i][j].getPieceType());
            System.out.print("\n");
        }

        char columnLabel = 65;
        for (i = 0; i < numCol; i++)
        {
            System.out.print(String.valueOf(" " + columnLabel + " "));
            columnLabel++;
        }
        System.out.print("\n\n");
    }

    public boolean placePiece(String location, String pieceType) {
        filled = true;

        for (int i = 6; i > 0; i--)
        {
            if (board[i][1 + location.charAt(0) - 65].getPieceType().equals(" + ")) {
                row = i;
                col = 1 + location.charAt(0) - 65;
                board[row][col].setPieceType(pieceType);
                filled = false;
                break;
            }
        }

        if (filled)
        {
            System.out.println("Column full. Choose another column.");
            return false;
        }

        return true;
    }
}
