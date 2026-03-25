package main;

import main.cli.CliController;
import main.service.CalculatorManager;
import main.service.CalculatorGateway;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {


        CliController cliController = new CliController(new Scanner(System.in));
        CalculatorManager<Double> calculator = new CalculatorManager<>(cliController, Double::parseDouble);
        CalculatorGateway program = new CalculatorGateway(calculator);

        program.run();
    }








    //    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        Calculator<Double> calculator = new Calculator<>(sc, Double::parseDouble);
//
//        // system exit is implemented in Calculator class
//        while (true) {
//
//            Double lhs = calculator.getOperand();
//            Double rhs = calculator.getOperand();
//            OperatorType operator = calculator.getOperator();
//            CalculationRecord record = calculator.calculate(lhs, rhs, operator);
//
//            calculator.printRecord(record);
//            calculator.addRecord(record);
//        }
//    }
}
