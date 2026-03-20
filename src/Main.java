import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lhs = sc.nextInt();
        int rhs = sc.nextInt();

        System.out.println((lhs < 0 && rhs < 0) ? "잘못된 입력값입니다 0 포함 양의 정수를 입력하셔야 합니다." : "OK");

        sc.close();
    }
}