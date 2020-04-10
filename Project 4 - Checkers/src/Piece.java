public class Piece {
    protected String pieceType;
    protected boolean king;

    public Piece(){
        this.pieceType = " + ";
        this.king = false;
    }

    public Piece(String pieceType) {
        this.pieceType = pieceType;
        this.king = false;
    }

    public Piece(int turn){
        if (turn == 0)
            this.pieceType = " X ";
        else
            this.pieceType = " O ";
    }

    public void setPieceType(String pieceType){
        this.pieceType = pieceType;
    }

    public String getPieceType(){
        return pieceType;
    }

    public String getOtherPieceType() {
        if (pieceType.equals(" X "))
            return " O ";
        else if (pieceType.equals(" O "))
            return " X ";
        else if (pieceType.equals("xKx"))
            return "oKo";
        else
            return "xKx";
    }

    public boolean getKing() {
        return king;
    }

    public void setKing(boolean king) {
        this.king = king;
    }
}
