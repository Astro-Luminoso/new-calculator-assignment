
package main.service;

import main.Exception.UnauthorizedInputException;
import main.cli.CliController;
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
public class CalculatorManager<T extends Number> {

    private final CliController scanner;
    private final Function<String, T> parser;

    private final CalculatorRecorder recorder;

    /**
     * Calculator Class Constructor
     * <br/>
     * 클래스 Calculator 생성자
     *
     * @param scanner a Scanner object to receive user input (사용자 입력을 받기 위한 Scanner 객체)
     * @param parser a function object to parse string value to number type (문자열 값을 숫자 타입으로 변환하기 위한 함수 객체)
     */
    public CalculatorManager(CliController scanner, Function<String, T> parser, CalculatorRecorder recorder) {
        this.scanner = scanner;
        this.recorder = recorder;
        this.parser = parser;
    }

    /**
     * get an operand as string value from scanner and parse it to integer
     * <br/>
     * 사용자 입력으로 피연산자를 반환
     *
     * @return integer value of operand (피연산자의 정수 값)
     *
     */
     private T getOperand() {
        while(true) {

            try {
                String value = scanner.getValue("숫자를 입력하여 주시옵소서.", "^(?:-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)|#)$");

                if (value.equals("#")) return null; // cancel button

                return this.parser.apply(value);

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
    private OperatorType getOperator() {

        OperatorType type = null;

        System.out.println();
        while (type == null) {
            try {
                char value = scanner.getValue("연산자를 입력하여 주시옵소서. +, -, *, / 중 하나를 입력해주시옵소서.").charAt(0);
                if (value == '#') return null;

                type = OperatorType.getOperator(value);
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
    private CalculationRecord calculate(T lhs, T rhs, OperatorType operator) {

        return new CalculationRecord (
                lhs.doubleValue(), rhs.doubleValue(), operator, operator.calculate(lhs.doubleValue(), rhs.doubleValue()));
    }


    public void compute() {
        T lhs = this.getOperand();
        if (lhs == null) return ;
        T rhs = this.getOperand();
        if (rhs == null) return;
        OperatorType operator = this.getOperator();
        if (operator == null) return;
        CalculationRecord record = this.calculate(lhs, rhs, operator);

        scanner.cliPrint(record);
        recorder.addRecord(record);
    }

    public void queryRecord() {
        String value = null;
        while (value == null) {
             value = scanner.getValue("기록을 조회하시려면 숫자를 입력하여 주시옵소서. 입력한 숫자보다 작은 결과값을 가진 기록이 조회됩니다.", "^(?:-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)|#)$");
        }
        if (value.equals("#")) return;

        scanner.cliPrint(recorder.getRecord(Double.parseDouble(value)));
    }


    public void deleteRecord() {

        this.recorder.removeRecord();
    }

    public int getMenu() {

        String value = scanner.getValue("메뉴를 입력하여 주시옵소서. 1: 계산하기, 2: 기록 보기, 3: 기록 삭제, 4: 프로그램 종료, 5: 메뉴 보기");

        if(value.equals("exit"))
            return -1;

        if(value.matches("^[1-3]$")) {
            return Integer.parseInt(value);
        } else {
            throw new UnauthorizedInputException();
        }
    }
}
