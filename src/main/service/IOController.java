package main.service;

import main.Exception.UnauthorizedInputException;
import main.cli.CliController;
import main.enumerate.OperatorType;

import java.util.function.Function;
import java.util.function.Supplier;

public class IOController {

    private final CliController scanner;


    public IOController(CliController sc) {

        this.scanner = sc;
    }

    // C#에서도 ref 키워드가 있었고
    // C언어도 포인터 -어드레스 값으로 함수를 인자로 가져올 수 있는데
    // Java도 비슷한게있지 았았을까? - 해서 AI 돌려봄
    public <U> U loopMethod (Supplier<U> action){

        while(true) {
            try{
                return action.get();
            } catch (UnauthorizedInputException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public int returnMenuValue() {

        String value = scanner.getValue("메뉴를 입력하여 주시옵소서. 1: 계산하기, 2: 기록 보기, 3: 기록 삭제, exit: 프로그램 종료");

        if (value.equals("exit")) return -1;

        if (value.matches("^[1-3]$")) {
            return Integer.parseInt(value);
        } else {
            throw new UnauthorizedInputException();
        }
    }

    public <T extends Number> T returnOperandValue(Function<String, T> parser) {

        String value = scanner.getValue("숫자를 입력하여 주시옵소서.", "^(?:-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)|#)$");

        if (value.equals("#")) return null; // cancel button

        return parser.apply(value);
    }

    public OperatorType returnOperatorType() {

        char value = scanner.getValue("연산자를 입력하여 주시옵소서. +, -, *, / 중 하나를 입력해주시옵소서.").charAt(0);
        if (value == '#') return null;

        return OperatorType.getOperator(value);
    }

    public String returnQueryUpbound() {

        return scanner.getValue("기록을 조회하시려면 숫자를 입력하여 주시옵소서. 입력한 숫자보다 작은 결과값을 가진 기록이 조회됩니다.", "^(?:-?(?:\\d+(?:\\.\\d+)?|\\.\\d+)|#)$");
    }


    public void printResult(Object result) {

        scanner.cliPrint(result);
    }
}
