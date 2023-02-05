package peakfinder;

import java.util.Arrays;

public class PeakFinder1D {

    public static int find(int[] data) {

        int target;
        int left = 0, right = data.length - 1;

        while (left < right) {
            target = left + (right - left) / 2;

            if (data[target] < data[target + 1]) {
                left = target + 1;
            } else {
                right = target;
            }
        }

        return left;
    }

    public static int[] getRandomData(int count) {
        int[] data = new int[count];

        for (int i = 0; i < data.length; i++) {
            data[i] = (int) (Math.random() * 10 + 1);
        }

        return data;
    }

    public static void main(String[] args) {
        int[] data = getRandomData(20);

        int index = find(data);

        System.out.println(Arrays.toString(data));
        System.out.printf("Peak is %d (index: %d)", data[index], index);
    }

}
