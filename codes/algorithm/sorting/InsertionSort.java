package sorting;

public class InsertionSort implements Sort {

    /**
     * Sort the data in ascending order
     *
     * @param data target sorting data array
     */
    public void sort(int[] data) {

        for (int i = 1; i < data.length; i++) {
            int target = data[i]; // Target data to sort

            int j; // data of sorted subarray
            for (j = i - 1; j >= 0; j--) {
                // break if target data is larger than sorted data
                if (data[j] <= target) {
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
