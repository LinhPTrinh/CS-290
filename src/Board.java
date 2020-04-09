public abstract class Board {
    protected Piece[][] board;
    protected boolean filled;
    protected int numCol;
    protected int numRow;
    protected boolean win;
    protected int row;
    protected int col;

    public void initBoard(){
        int i, j;
        board = new Piece[numRow+2][numCol+2];

        for (i = 0; i < numRow+2; i++)
        {
            for (j = 0; j < numCol+2; j++)
            {
                board[i][j] = new Piece();

                // Set border
                if (i == 0 || j == 0)
                    board[i][j].setPieceType("B");
                else if (i == numRow+1 || j == numCol+1)
                    board[i][j].setPieceType("B");
            }
        }
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
}
