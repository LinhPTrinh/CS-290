public class CheckersPiece extends Piece {
    @Override
    public void setPieceType(String pieceType) {
        if (pieceType.equals(" X ") && king)
            this.pieceType = "xKx";
        else if (pieceType.equals(" O ") && king)
            this.pieceType = "oKo";
        else
            this.pieceType = pieceType;
    }
}
