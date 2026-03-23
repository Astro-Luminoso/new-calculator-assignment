package main.dmo;

/**
 * A record class to store details of calculations
 * <br/>
 * 계산의 세부사항을 정리하기 위한 record class
 *
 * @param lfs left operand (연산자 기준 좌측 피연산자)
 * @param rhs right operand (연산자 기준 우측 피연산자)
 * @param operator operator used in this calculation (계산에 사용된 연산자)
 * @param result the results of this calculation (이 계산의 결과)
 */
public record CalculationRecord(int lfs, int rhs, char operator, int result) {

    @Override
    public String toString() {
        return String.format("[%d %c %d = %d]", lfs, operator, rhs, result);
    }
}
