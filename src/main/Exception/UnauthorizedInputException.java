package main.Exception;

/**
 * 유효한 값이 아닌 입력값에 대한 예외처리
 */
public class UnauthorizedInputException extends IllegalArgumentException {

    public UnauthorizedInputException() {
        super("올바르지 않은 입력값이옵니다. 다시 입력하여 주시옵소서.");
    }
    public UnauthorizedInputException(String message) {
        super(message);
    }
}
