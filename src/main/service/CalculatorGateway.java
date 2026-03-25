package main.service;

public class CalculatorGateway {


    private final CalculatorManager manager;

    public CalculatorGateway(CalculatorManager manager) {

        this.manager = manager;
    }


    private void executeMenu(int menu) {
        switch (menu) {

            case 1 -> manager.compute();
        }
    }



    public void run() {

        while (true) {

            this.executeMenu(manager.getMenu());

        }
    }

}
