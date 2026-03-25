package main.cli;

import main.Exception.UnauthorizedInputException;

import java.util.Scanner;

public class CliController {

    private final Scanner scanner;

    public CliController (Scanner scanner) {

        this.scanner = scanner;
    }

    public String getValue(String message) {

        System.out.println(message);
        return scanner.nextLine();
    }

    /**
     * get user input with regex constraint until the value is valid
     * <br />
     * regex 제약 조건을 만족하는 유효한 입력값이 나올 때까지 사용자 입력을 받는 메소드
     *
     * @param regex a regular expression to validate user input (사용자 입력값의 유효성을 검증하기 위한 정규 표현식)
     * @return String value of user input that satisfies the regex constraint (regex 제약 조건을 만족하는 사용자 입력값의 문자열 값)
     */
    public String getValue(String message, String regex) {

        String value = this.getValue(message);

        if (value.matches(regex)) {
            return value;
        } else {
            throw new UnauthorizedInputException();
        }
    }

    public void cliPrint(Object object) {

        System.out.println(object);
    }
}
