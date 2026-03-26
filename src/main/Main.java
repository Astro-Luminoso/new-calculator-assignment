package main;

import main.cli.CliController;
import main.dmo.CalculationRecord;
import main.service.CalculatorManager;
import main.service.CalculatorGateway;
import main.service.CalculatorRecorder;
import main.service.IOController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        List<CalculationRecord> records = new ArrayList<>();
        CalculatorRecorder recorder = new CalculatorRecorder(records);

        CliController cliController = new CliController(new Scanner(System.in));
        IOController controller = new IOController(cliController);

        CalculatorManager<Double> calculator = new CalculatorManager<>(controller, Double::parseDouble, recorder);
        CalculatorGateway program = new CalculatorGateway(calculator);
        program.run();
    }
}
