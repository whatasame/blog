package algorithm.sorting;

public class InsertionSort {

    public static void sort(int[] data) {

        for (int i = 1; i < data.length; i++) {
            int target = data[i];

            int j;
            for (j = i - 1; j >= 0; j--) {
                if (data[j] <= target) {
                    break;
                }

                data[j + 1] = data[j];
            }
            data[j + 1] = target;
        }

    }

}
