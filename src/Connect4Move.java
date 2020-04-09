public class Connect4Move extends Move{
    public Boolean checkValid(){
        input = input.trim();

        if (input.length() != 1 || input.charAt(0) < 65 || input.charAt(0) > 71 && !input.equals("Z"))
            return false;
        else
            return true;
    }
}
