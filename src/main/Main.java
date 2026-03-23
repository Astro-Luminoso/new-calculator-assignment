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
        while(true) {

            calculator.setUpOperands();
            calculator.setUpOperator();
            calculator.calculate();

        }
    }


}