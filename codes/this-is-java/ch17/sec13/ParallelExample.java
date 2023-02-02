package ch17.sec13;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelExample {

    /**
     * Java 8: Stream이 빠름 (약 7000ms )
     * Java 11: ParallelStream이 빠름 (약 30ms)
     * Java 17: ParallelStream이 빠름 (약 30ms)
     */

    public static void main(String[] args) {
        Random random = new Random();

        List<Integer> scoreList = new ArrayList<>();
        int count = 100000000;
        while (count-- > 0) {
            scoreList.add(random.nextInt(101)); // 0 ~ 100 사이의 난수
        }

        double avg;
        long startTime, endTime, streamTime, parallelTime;

        startTime = System.currentTimeMillis();
        avg = scoreList.stream()
                .mapToInt(i -> i.intValue())
                .average()
                .getAsDouble();
        endTime = System.currentTimeMillis();
        streamTime = endTime - startTime;
        System.out.println("[Stream] avg = " + avg + " (" + streamTime + " ms)");


        startTime = System.currentTimeMillis();
        avg = scoreList.parallelStream()
                .mapToInt(i -> i.intValue())
                .average()
                .getAsDouble();
        endTime = System.currentTimeMillis();
        parallelTime = endTime - startTime;
        System.out.println("[Parallel stream] avg = " + avg + " (" + parallelTime + " ms)");

        long diff = (streamTime - parallelTime) > 0 ? streamTime - parallelTime : parallelTime - streamTime;
        System.out.println("시간 차이: " + diff + " ms");

    }
}
