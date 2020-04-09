public class CheckersMove extends Move {
    public Boolean checkValid() {
        input = input.replaceAll("\\s+", "");

        if ((input.length() != 4 || input.charAt(0) < 65 ||
                input.charAt(0) > 72 || input.charAt(2) < 65 || input.charAt(2) > 72) && input.charAt(0) != 90)
            return false;
        else {
            try {
                if (Integer.parseInt(input.substring(1, 2)) > 8)
                    return false;

                if (Integer.parseInt(input.substring(3, 4)) > 8)
                    return false;
            }
            catch (NumberFormatException nfe) {
                return false;
            }

            return true;
        }
    }
}
