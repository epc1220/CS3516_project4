public class InvalidInstructionException extends Exception {
    public InvalidInstructionException(String instruction) {
        super(String.format("Invalid instruction entry, \"%s\". Skipping...\n", instruction));
    }
}
