
package main.service;

import main.dmo.CalculationRecord;
import main.enumerate.OperatorType;

import java.util.function.Function;

/**
 * 계산기 프로그램을 실제로 실행하는 클래스
 */
public class CalculatorManager<T extends Number> {

//    private final CliController scanner;
    private final IOController controller;
    private final Function<String, T> parser;

    private final CalculatorRecorder recorder;

    /**

     * 클래스 Calculator 생성자
     *
     * @param controller 사용자 입력 관리를 위한 객체
     * @param parser 문자열 값을 숫자 타입으로 변환하기 위한 함수 객체
     * @param recorder 계산 기록 관리를 위한 객체
     */
    public CalculatorManager(IOController controller, Function<String, T> parser, CalculatorRecorder recorder) {
        this.controller = controller;
        this.recorder = recorder;
        this.parser = parser;
    }

    /**
     * 사용자 입력으로 피연산자를 반환
     *
     * @return 피연산자의 정수 값
     */
     private T getOperand() {

         return controller.loopMethod(() -> controller.returnOperandValue(parser));
    }

    /**
     * 사용자 입력으로 계산의 연산자를 설정
     *
     * @return 연산자의 문자 값
     */
    private OperatorType getOperator() {

        return controller.loopMethod(controller::returnOperatorType);
    }

    /**
     * 사용자의 입력값을 기반으로 계산한다
     *
     * @param lhs 좌항 피연산자
     * @param rhs 우항 피연산자
     * @param operator 계산을 위한 연산자
     * @return 계산 결과를 담은 CalculationRecord 객체
     */
    private CalculationRecord calculate(T lhs, T rhs, OperatorType operator) {

        return new CalculationRecord (
                lhs.doubleValue(), rhs.doubleValue(), operator, operator.calculate(lhs.doubleValue(), rhs.doubleValue()));
    }


    /**
     * 계산 로직을 수행하는 메소드
     */
    public void compute() {
        T lhs = this.getOperand();
        if (lhs == null) return ;
        T rhs = this.getOperand();
        if (rhs == null) return;
        OperatorType operator = this.getOperator();
        if (operator == null) return;

        try{
            CalculationRecord record = this.calculate(lhs, rhs, operator);
            controller.printResult(record);
            recorder.addRecord(record);
        } catch (ArithmeticException e) {
            controller.printResult(e.getMessage());
        }

    }

    /**
     * 계산 기록을 최댓값 기준으로 조회
     */
    public void queryRecord() {

        String value = controller.loopMethod(controller::returnQueryUpbound);

        if (value.equals("#")) return;

        controller.printResult(recorder.getRecord(Double.parseDouble(value)));
    }


    /**
     * 첫번째 계산 기록을 삭제
     */
    public void deleteRecord() {

        this.recorder.removeRecord();
        controller.printResult("첫 번째 기록이 삭제되었습니다.");
    }

    /**
     * 매뉴 옵션을 받는 메소드
     *
     * @return 1-3 까지의 정수 그리고 exit 문자열시 -1
     */
    public int getMenu() {

        return controller.loopMethod(controller::returnMenuValue);
    }
}
