package main;
import main.dmo.CalculationRecord;
import main.service.Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<CalculationRecord> records = new ArrayList<>();
        Calculator calculator = new Calculator(sc, records);

        // system exit is implemented in Calculator class
        while (true) {

            int lhs = calculator.getOperand();
            int rhs = calculator.getOperand();
            char operator = calculator.getOperator();
            CalculationRecord record = calculator.calculate(lhs, rhs, operator);

            calculator.printRecord(record);
            calculator.addRecord(record);



        }
    }


}