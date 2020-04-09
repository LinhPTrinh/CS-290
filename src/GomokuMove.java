public class GomokuMove extends Move{

    public Boolean checkValid(){

        input = input.trim();

        if ((input.length() < 2 || input.charAt(0) < 65 || input.charAt(0) > 83) && input.charAt(0) != 90)
            return false;
        else {
            String[] rowNumber = input.substring(1).trim().split(" ");

            if (rowNumber.length > 1)
                return false;

            try {
                if (Integer.parseInt(rowNumber[0]) > 19)
                    return false;
            }
            catch (NumberFormatException nfe) {
                return false;
            }

            return true;
        }
    }
}
