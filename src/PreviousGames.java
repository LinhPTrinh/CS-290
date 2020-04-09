public abstract class PreviousGames extends Board{
    protected int numPiecetoWin;

    public abstract boolean placePiece(String location, String pieceType);

    public boolean Win(String pieceType){
        int i, j;
        int consecutivePiece = 0;

        if (pieceType.equals(board[row][col].getPieceType()))
        {
            consecutivePiece++;
            if (!win)
                search(board, pieceType, row, col, -1, -1, consecutivePiece);

            if (!win)
                search(board, pieceType, row, col, -1, 0, consecutivePiece);

            if (!win)
                search(board, pieceType, row, col, -1, 1, consecutivePiece);

            if (!win)
                search(board, pieceType, row, col, 0, -1, consecutivePiece);

            if (!win)
                search(board, pieceType, row, col, 0, 1, consecutivePiece);

            if (!win)
                search(board, pieceType, row, col, 1, -1, consecutivePiece);

            if (!win)
                search(board, pieceType, row, col, 1, 0, consecutivePiece);

            if (!win)
                search(board, pieceType, row, col, 1, 1, consecutivePiece);
        }

        return win;
    }

    public void search(Piece[][] board, String pieceType, int x, int y, int offsetX, int offsetY, int consecutivePiece)
    {

        if (board[x+offsetX][y+offsetY].getPieceType().equals("B"))
        {
            win = false;
            return;
        }

        if (!board[x+offsetX][y+offsetY].getPieceType().equals(pieceType))
        {
            win = false;
            return;
        }

        consecutivePiece++;

        if (consecutivePiece == numPiecetoWin)
        {
            win = true;
            return;
        }

        search(board, pieceType, x+offsetX, y+offsetY, offsetX, offsetY, consecutivePiece);
    }
}
