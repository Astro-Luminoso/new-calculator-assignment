package main;
import main.dmo.CalculationRecord;
import main.enumerate.OperatorType;
import main.service.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Calculator<Double> calculator = new Calculator<>(sc, Double::parseDouble);

        // system exit is implemented in Calculator class
        while (true) {

            Double lhs = calculator.getOperand();
            Double rhs = calculator.getOperand();
            OperatorType operator = calculator.getOperator();
            CalculationRecord record = calculator.calculate(lhs, rhs, operator);

            calculator.printRecord(record);
            calculator.addRecord(record);
        }
    }
}
