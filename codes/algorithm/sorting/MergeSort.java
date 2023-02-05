package sorting;

import java.util.Arrays;

public class MergeSort implements Sort {

    public void sort(int[] data, int left, int right) {
        if (left < right) {
            int middle = left + ((right - left) / 2); // Half index of data

            sort(data, left, middle); // Sort left subarray
            sort(data, middle + 1, right); // Sort right subarray

            merge(data, left, middle, right); // Merge subarray
        }
    }

    public void merge(int[] data, int left, int mid, int right) {

        // Divide original data into two subarray
        int[] leftArray = Arrays.copyOfRange(data, left, mid + 1);
        int[] rightArray = Arrays.copyOfRange(data, mid + 1, right + 1);

        // Compare two subarray and merge
        int i = left; // merged array index
        int l = 0, r = 0; // left, right subarray index
        for (; l < leftArray.length && r < rightArray.length; i++) {
            if (leftArray[l] < rightArray[r]) {
                data[i] = leftArray[l];
                l++;
            } else {
                data[i] = rightArray[r];
                r++;
            }
        }

        // Merge remain data after compare
        while (l < leftArray.length) {
            data[i] = leftArray[l];
            l++;
            i++;
        }
        while (r < rightArray.length) {
            data[i] = rightArray[r];
            r++;
            i++;
        }

    }

    @Override
    public void run(int[] data) {
        sort(data, 0, data.length - 1);
    }
}
