
package main.service;

import main.Exception.UnauthorizedInputException;
import main.dmo.CalculationRecord;
import main.enumerate.OperatorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

/**
 * An actual class to run calculator program
 * <br/>
 * 계산기 프로그램을 실제로 실행하는 클래스
 */
public class Calculator <T extends Number> {


    private final Scanner scanner;

    // record previous calculations
    private final List<CalculationRecord> records;

    private final Function<String, T> parser;

    /**
     * Calculator Class Constructor
     * <br/>
     * 클래스 Calculator 생성자
     *
     * @param scanner a Scanner object to receive user input (사용자 입력을 받기 위한 Scanner 객체)
     * @param parser a function object to parse string value to number type (문자열 값을 숫자 타입으로 변환하기 위한 함수 객체)
     */
    public Calculator(Scanner scanner, Function<String, T> parser) {
        this.scanner = scanner;
        this.records = new ArrayList<>();
        this.parser = parser;
    }

    /**
     * a method to get user
     * 사용자 입력을 받는 메소드
     *
     * @return a string value of user input (유저의 입력값의 문자열 값)
     */
    private String getValue() {

        String value = scanner.nextLine();
        if (value.equalsIgnoreCase("exit")) {
            System.out.println("계산기를 종료하옵니다. 이용해주셔서 성은이 망극하옵나이다.");
            System.exit(0);
        }

        return value;
    }


    /**
     * get user input with regex constraint until the value is valid
     * <br />
     * regex 제약 조건을 만족하는 유효한 입력값이 나올 때까지 사용자 입력을 받는 메소드
     *
     * @param regex a regular expression to validate user input (사용자 입력값의 유효성을 검증하기 위한 정규 표현식)
     * @return String value of user input that satisfies the regex constraint (regex 제약 조건을 만족하는 사용자 입력값의 문자열 값)
     */
    private String getValue(String regex) {

        String value = this.getValue();

        if (value.matches(regex)) {
            return value;
        } else {
            throw new UnauthorizedInputException();
        }


    }

    /**
     * get an operand as string value from scanner and parse it to integer
     * <br/>
     * 사용자 입력으로 피연산자를 반환
     *
     * @return integer value of operand (피연산자의 정수 값)
     */
    public T getOperand() {

        System.out.println("숫자를 입력하여 주시옵소서.");
        while(true) {
            try {
                return this.parser.apply(this.getValue("^-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)$"));
            } catch (UnauthorizedInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * get an operator of calculation from scanner and return input value as character
     * <br/>
     * 사용자 입력으로 계산의 연산자를 설정
     *
     * @return character value of operator (연산자의 문자 값)
     */
    public OperatorType getOperator() {

        OperatorType type = null;

        System.out.println("연산자를 입력하여 주시옵소서. +, -, *, / 중 하나를 입력해주시옵소서.");
        while (type == null) {
            try {

                type = OperatorType.getOperator(this.getValue().charAt(0));
            } catch (UnauthorizedInputException e) {
                System.out.println(e.getMessage());
            }
        }

        return type;
    }

    /**
     * calculate based on user input
     * <br/>
     * 사용자의 입력값을 기반으로 계산한다
     *
     * @param lhs left-hand side operand (좌항 피연산자)
     * @param rhs right-hand side operand (우항 피연산자)
     * @param operator operator for calculation (계산을 위한 연산자)
     * @return a CalculationRecord object that contains the calculation result (계산 결과를 담은 CalculationRecord 객체)
     */
    public CalculationRecord calculate(T lhs, T rhs, OperatorType operator) {

        return new CalculationRecord (
                lhs.doubleValue(), rhs.doubleValue(), operator, operator.calculate(lhs.doubleValue(), rhs.doubleValue()));
    }

    /**
     * print calculation record to console
     * <br />
     * 계산 기록을 콘솔에 출력
     *
     * @param record a CalculationRecord object to be printed (출력할 CalculationRecord 객체)
     */
    public void printRecord(CalculationRecord record) {

        System.out.println(record);
    }

    /**
     * add calculator record into the record list
     * <br />
     * 계산 기록을 기록 리스트에 추가
     * @param record a CalculationRecord object to be added (출력할 CalculationRecord 객체)
     */
    public void addRecord(CalculationRecord record) {
        this.records.add(record);
    }
}
