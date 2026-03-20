import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {

            String lhsInput = sc.nextLine();
            String rhsInput = sc.nextLine();

            // sanitize input value
            if (lhsInput.equals("exit") || rhsInput.equals("exit")) {
                System.out.println("계산기를 종료하겠 사옵나이다.");
                break;
            } else if (lhsInput.contains(".") || rhsInput.contains(".")) {
                System.out.println("소수는 입력할 수 없사옵니다.");
                continue;
            } else if (lhsInput.contains("-") || rhsInput.contains("-")) {
                System.out.println("음의 정수는 입력할 수 없사옵니다.");
                continue;
            }

            // parse String to int
            int lhs = Integer.parseInt(lhsInput);
            int rhs = Integer.parseInt(rhsInput);

            // get four basic arithmetic Operators
            char operator = sc.next().trim().charAt(0);

            // results
            switch(operator) {
                case '+' -> System.out.println(lhs + rhs);
                case '-' -> System.out.println(lhs - rhs);
                case '*' -> System.out.println(lhs * rhs);
                case '/' -> {
                    if (rhs == 0) {
                        System.out.println("0으로 나누는 행위는 아니되옵니다.");
                        return;
                    }
                    System.out.println(lhs/rhs);
                }
                default -> System.out.println("지원하지 않는 연산자이옵니다.");
            }
        }

        sc.close();
    }
}