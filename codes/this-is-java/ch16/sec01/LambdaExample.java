package ch16.sec01;

public class LambdaExample {

    public static void main(String[] args) {
        action(((x, y) -> { // action()의 매개변수 calculable을 람다식을 통해 익명 구현 객체의 calculate()를 정의 후 Calculable 객체 전달
            int result = x + y;
            System.out.println("result = " + result);
        }));

        action(((x, y) -> {
            int result = x - y;
            System.out.println("result = " + result);
        }));
    }

    public static void action(Calculable calculable) {
        int x = 10;
        int y = 4;

        calculable.calculate(x, y);
    }
}
