package main.Exception;

/**
 * Custom Exception for invalid input value
 * <br/>
 * 유효한 값이 아닌 입력값에 대한 예외처리
 */
public class UnauthorizedInputException extends IllegalArgumentException {

    public UnauthorizedInputException(String message) {
        super(message);
    }
}
