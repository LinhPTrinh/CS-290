public class GomokuMove extends Move{

    public Boolean checkValid(){

        input = input.trim();

        if ((input.length() < 2 || input.charAt(0) < 65 || input.charAt(0) > 83) && input.charAt(0) != 90) {
            System.out.println("Invalid move. Try again.");
            return false;
        }
        else {
            String[] rowNumber = input.substring(1).trim().split(" ");

            if (rowNumber.length > 1) {
                System.out.println("Invalid move. Try again.");
                return false;
            }

            try {
                if (Integer.parseInt(rowNumber[0]) > 19) {
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
    public void findLegalMoves(String pieceType, Board game) {};
}
