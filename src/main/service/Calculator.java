
package main.service;

import main.Exception.NotAllowedInputException;
import main.Exception.UnauthorizedInputException;
import main.dmo.CalculationRecord;

import java.util.List;
import java.util.Scanner;

public class Calculator {

    private final Scanner scanner;
    private final List<CalculationRecord> records;

    private int lhs;
    private int rhs;
    private char operator;

    private static final String OPERAND_CONSTRAINT = "OPERAND";
    private static final String OPERATOR_CONSTRAINT = "OPERATOR";


    public Calculator(Scanner scanner, List<CalculationRecord> records) {
        this.scanner = scanner;
        this.records = records;
    }

    private boolean sanitizeValue(String constraint, String value) {

        try {
            switch (constraint) {
                case OPERAND_CONSTRAINT:
                    if (!value.matches("\\d"))
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

    public void setUpOperands() {
        this.lhs = Integer.parseInt(this.getValue(OPERAND_CONSTRAINT));
        this.rhs = Integer.parseInt(this.getValue(OPERAND_CONSTRAINT));
    }

    public void setUpOperator() {
        this.operator = this.getValue(OPERATOR_CONSTRAINT).charAt(0);
    }

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
