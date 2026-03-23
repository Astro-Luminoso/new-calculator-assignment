package main.Exception;

public class UnauthorizedInputException extends IllegalArgumentException {

    public UnauthorizedInputException(String message) {
        super(message);
    }
}
