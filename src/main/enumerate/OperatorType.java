package main.enumerate;

import main.Exception.UnauthorizedInputException;
import java.util.Arrays;
import java.util.function.BiFunction;


public enum OperatorType {

    ADDITION('+', (lhs, rhs)-> lhs + rhs),
    SUBTRACTION('-', (lhs, rhs)-> lhs - rhs),
    MULTIPLICATION('*', (lhs, rhs)-> lhs * rhs),
    DIVISION('/', (lhs, rhs)-> lhs / rhs);

    private final char symbol;
    private final BiFunction<Double, Double, Double> calculate;

    OperatorType(char symbol, BiFunction<Double, Double, Double> calculate) {
        this.symbol = symbol;
        this.calculate = calculate;
    }

    public char getSymbol() {

        return this.symbol;
    }

   public double calculate(double lhs, double rhs) {
        return Math.round(this.calculate.apply(lhs, rhs) * 100) / 100.0;
    }

    public static OperatorType getOperator(char symbol) {

        return Arrays.stream(OperatorType.values())
                .filter(operatorType -> operatorType.symbol == symbol)
                .findFirst()
                .orElseThrow(() -> new UnauthorizedInputException("올바르지 않은 연산자이옵니다. +, -, *, / 중 하나를 입력해주시옵소서."));
    }
}
