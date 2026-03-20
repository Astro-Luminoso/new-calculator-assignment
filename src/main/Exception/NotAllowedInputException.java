package main.Exception;

public class NotAllowedInputException extends NumberFormatException {
    public NotAllowedInputException(String message) {
        super(message);
    }
}
