package algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Analysis {

    public static String dataType;

    public static int[] getAscendingData(int count) {
        /* Return data sorted in ascending order */
        dataType = "Ascending";
        int[] data = new int[count];

        for (int i = 0; i < count; i++) {
            data[i] = i + 1;
        }

        return data;
    }

    public static int[] getDescendingData(int count) {
        /* Return data sorted in descending order */
        dataType = "Descending";
        int[] data = new int[count];

        for (int i = 0; i < count; i++) {
            data[i] = count - i;
        }

        return data;
    }


    public static int[] getRandomData(int count) {
        /* Return random data */
        dataType = "Random";
        int[] data = new int[count];

        for (int i = 0; i < count; i++) {
            data[i] = (int) (Math.random() * count) + 1;
        }

        return data;
    }

    public static void main(String[] args) throws IOException {

        // Insert data count
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input data count: ");
        int dataCount = Integer.parseInt(br.readLine());

        // List of sorting algorithms
        List<Sort> sortList = new LinkedList<Sort>() {
            {
                add(new InsertionSort());
                add(new MergeSort());
                add(new QuickSort());
            }
        };

        // Make test case
        int[] testCase = getRandomData(dataCount);
        System.out.println("Data type: " + dataType);
        System.out.println();

        // Run and measure performance each sorting algorithm
        System.out.println("Execution time analysis");
        for (Sort sort : sortList) {
            int[] data = Arrays.copyOf(testCase, testCase.length);

            long start = System.currentTimeMillis();
            sort.run(data);
            long takenTime = System.currentTimeMillis() - start;
            System.out.println("* " + sort.getClass().getSimpleName() + ": " + takenTime + "ms"); // Print result
//            System.out.println("Original data: " + Arrays.toString(testCase));
//            System.out.println("Sorted data: " + Arrays.toString(data));
//            System.out.println();
        }

    }
}
