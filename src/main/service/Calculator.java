
package main.service;

import main.Exception.NotAllowedInputException;
import main.Exception.UnauthorizedInputException;
import main.dmo.CalculationRecord;

import java.util.List;
import java.util.Scanner;

/**
 * An actual class to run calculator program
 * <br/>
 * 계산기 프로그램을 실제로 실행하는 클래스
 */
public class Calculator {


    private final Scanner scanner;

    // record previous calculations
    private final List<CalculationRecord> records;

    private int lhs;
    private int rhs;
    private char operator;

    // constant value to control input validation
    private static final String OPERAND_CONSTRAINT = "OPERAND";
    private static final String OPERATOR_CONSTRAINT = "OPERATOR";


    /**
     * Calculator Class Constructor
     * <br/>
     * 클래스 Calculator 생성자
     *
     * @param scanner a Scanner object to receive user input (사용자 입력을 받기 위한 Scanner 객체)
     * @param records a List to store previous calculation records (이전 계산 기록을 저장하기 위한 List)
     */
    public Calculator(Scanner scanner, List<CalculationRecord> records) {
        this.scanner = scanner;
        this.records = records;
    }

    /**
     * inspect user input value
     * <br/>
     * 사용자의 입력값을 검사
     *
     * @param constraint a string value to determine which constraint to apply (어떤 제약조건을 적용할지 결정하기 위한 문자열 값)
     * @param value user input value (사용자의 입력값)
     * @return true if the value is valid, false otherwise (값이 유효하면 true, 그렇지 않으면 false)
     */
    private boolean sanitizeValue(String constraint, String value) {

        try {
            switch (constraint) {
                case OPERAND_CONSTRAINT:
                    if (!value.matches("-?\\d+"))
                        throw new UnauthorizedInputException("입력값이 유효하지 않사옵니다. 다시 입력하여 주시옵소서.");
                    else if (value.contains("-"))
                        throw new NotAllowedInputException("음의 정수는 입력할 수 없사옵니다.");
                    else if (value.contains("."))
                        throw new NotAllowedInputException("소수는 입력할 수 없사옵니다.");
                    break;
                case OPERATOR_CONSTRAINT:
                    if (!value.matches("[+\\-*/]"))
                        throw new UnauthorizedInputException("지원하지 않는 연산자이옵니다. 다시 입력하여 주시옵소서.");
                    break;
            }
        } catch (NotAllowedInputException | UnauthorizedInputException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * a method to get user input until the value is valid
     * <br/>
     * 값이 유효할 때까지 사용자 입력을 받는 메소드
     *
     * @param constraint a string value to determine which constraint to apply (어떤 제약조건을 적용할지 결정하기 위한 문자열 값)
     * @return a string value of user input (유저의 입력값의 문자열 값)
     */
    private String getValue(String constraint) {

        boolean keepLooping = true;
        String result = null;

        while (keepLooping) {
            result = scanner.nextLine();

            if(result.equals("exit")) System.exit(0);
            if(this.sanitizeValue(constraint, result))
                keepLooping = false;
        }

        return result;

    }

    /**
     * set up operands by user input
     * <br/>
     * 사용자 입력으로 피연산자를 설정
     */
    public void setUpOperands() {
        this.lhs = Integer.parseInt(this.getValue(OPERAND_CONSTRAINT));
        this.rhs = Integer.parseInt(this.getValue(OPERAND_CONSTRAINT));
    }

    /**
     * set up an operator of calculation by user input
     * <br/>
     * 사용자 입력으로 계산의 연산자를 설정
     */
    public void setUpOperator() {
        this.operator = this.getValue(OPERATOR_CONSTRAINT).charAt(0);
    }

    /**
     * calculate based on user input
     * <br/>
     * 사용자의 입력값을 기반으로 계산한다
     */
    public void calculate() {

        CalculationRecord record = null;

        try {
            switch (this.operator) {
                case '+' -> record = new CalculationRecord(lhs, rhs, operator, lhs + rhs);
                case '-' -> record = new CalculationRecord(lhs, rhs, operator, lhs - rhs);
                case '*' -> record = new CalculationRecord(lhs, rhs, operator, lhs * rhs);
                case '/' -> {
                    if (rhs == 0) {
                        throw new ArithmeticException("0으로 나누는 행위는 아니되옵니다.");

                    }
                    record = new CalculationRecord(lhs, rhs, operator, lhs / rhs);
                }
            }
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(record);
        this.records.add(record);
    }
}
