package main.Exception;

/**
 * Custom Exception for valid input but cannot use due to constraint
 * <br/>
 * 유효한 값이긴 하나 허락되지 않은 입력값에 대한 예외처리
 *
 */
public class NotAllowedInputException extends NumberFormatException {
    public NotAllowedInputException(String message) {
        super(message);
    }
}
