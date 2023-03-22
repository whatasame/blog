package example.thisisjava.ch16.sec02.exam01;

import example.thisisjava.ch16.sec04.Person;

public class LambdaExample {
    public static void main(String[] args) {
        example.thisisjava.ch16.sec04.Person person = new Person();

        person.action(() -> {
            System.out.println("출근을 합니다.");
            System.out.println("프로그래밍을 합니다.");
        });

        person.action(() -> System.out.println("퇴근합니다."));
    }
}
