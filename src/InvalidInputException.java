public class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super("Invalid input. " + msg);
    }
}
