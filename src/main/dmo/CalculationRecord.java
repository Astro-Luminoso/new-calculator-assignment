package main.dmo;

public record CalculationRecord(int lfs, int rhs, char operator, int result) {

    @Override
    public String toString() {
        return String.format("[%d %c %d = %d]", lfs, operator, rhs, result);
    }
}
