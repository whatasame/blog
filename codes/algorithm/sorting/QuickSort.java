package sorting;

public class QuickSort implements Sort {

    public void sort(int[] data, int left, int right) {
        int divideIndex = partition(data, left, right);
        if (left < divideIndex - 1) {
            sort(data, left, divideIndex - 1);
        }
        if (divideIndex < right) {
            sort(data, divideIndex, right);
        }

    }

    private int partition(int[] data, int left, int right) {
        int pivot = data[(left + right) / 2];
        while (left <= right) {
            while (data[left] < pivot) {
                left++;
            }
            while (data[right] > pivot) {
                right--;
            }

            if (left <= right) {
                swap(data, left, right);
                left++;
                right--;
            }
        }

        return left;
    }

    private void swap(int[] data, int left, int right) {
        int tmp = data[left];
        data[left] = data[right];
        data[right] = tmp;
    }

    @Override
    public void run(int[] data) {
        sort(data, 0, data.length - 1);
    }
}
