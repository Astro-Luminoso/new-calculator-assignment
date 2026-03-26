package main.cli;

import main.Exception.UnauthorizedInputException;

import java.util.Scanner;

public class CliController {

    private final Scanner scanner;

    public CliController (Scanner scanner) {

        this.scanner = scanner;
    }

    /**
     * 사용자의 입력값을 문자열로 반환
     *
     * @param message 사용자에게 입력을 요청하는 메시지
     * @return 사용자의 입력값
     */
    public String getValue(String message) {

        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * regex 제약 조건을 만족하는 유효한 입력값이 나올 때까지 사용자 입력을 받는 메소드
     *
     * @param message 사용자에게 입력을 요청하는 메시지
     * @param regex 사용자 입력값의 유효성을 검증하기 위한 정규 표현식
     * @return regex 제약 조건을 만족하는 사용자 입력값의 문자열 값
     */
    public String getValue(String message, String regex) {

        String value = this.getValue(message);

        if (value.matches(regex)) {
            return value;
        } else {
            throw new UnauthorizedInputException();
        }
    }

    /**
     * 사용자에게 정보를 출력
     * @param object 출력할 정보 값
     */
    public void cliPrint(Object object) {

        System.out.println(object);
    }
}
