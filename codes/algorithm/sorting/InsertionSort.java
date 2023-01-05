package algorithm.sorting;

public class InsertionSort implements Sort {

    public void sort(int[] data) {

        for (int i = 1; i < data.length; i++) {
            int target = data[i]; // Target data to sort

            int j;
            for (j = i - 1; j >= 0; j--) {
                if (data[j] <= target) { // When find insertion index
                    break;
                }

                data[j + 1] = data[j]; // Move sorted data one space to the right
            }
            data[j + 1] = target; // Insertion index: j + 1
        }

    }

    @Override
    public void run(int[] data) {
        sort(data);
    }
}
