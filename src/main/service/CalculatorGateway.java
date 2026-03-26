package main.service;

public class CalculatorGateway {


    private final CalculatorManager<?> manager;

    public CalculatorGateway(CalculatorManager<?> manager) {

        this.manager = manager;
    }


    private void executeMenu(int menu) {
        switch (menu) {

            case 1 -> manager.compute();
            case 2 -> manager.queryRecord();
            case 3 -> manager.deleteRecord();
            case -1 -> System.exit(0);

        }
    }


    public void run() {

        while (true) {

            this.executeMenu(manager.getMenu());
        }
    }
}
