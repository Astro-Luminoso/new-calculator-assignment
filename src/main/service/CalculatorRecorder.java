package main.service;

import main.dmo.CalculationRecord;

import java.util.List;

public class CalculatorRecorder {

    private final List<CalculationRecord> records;

    public CalculatorRecorder(List<CalculationRecord> records) {
        this.records = records;
    }


    public void addRecord(CalculationRecord record) {

        this.records.add(record);
    }

    public void removeRecord() {

        if(records.toArray().length == 0) return;
        this.records.removeFirst();
    }

    public List<CalculationRecord> getRecord(double upBound) {



        return records.stream()
                .filter(record -> record.result() <= upBound)
                .toList();
    }

}
