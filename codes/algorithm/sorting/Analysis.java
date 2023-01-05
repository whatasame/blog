package algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Analysis {

    public static int[] getAscendingData(int count) {

        /* Return data sorted in ascending order */

        int[] data = new int[count];

        for (int i = 0; i < count; i++) {
            data[i] = i + 1;
            System.out.println(data[i]);
        }

        return data;
    }

    public static int[] getDescendingData(int count) {

        /* Return data sorted in descending order */

        int[] data = new int[count];

        for (int i = 0; i < count; i++) {
            data[i] = count - i;
        }

        return data;
    }


    public static int[] getRandomData(int count) {

        /* Return random data */

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
        System.out.println();

        // List of sorting algorithms
        List<Sort> sortList = new LinkedList<Sort>() {
            {
                add(new InsertionSort());
                add(new MergeSort());
            }
        };

        // Run sorting each algorithm
        for(Sort sort : sortList){
            int[] data = getRandomData(dataCount);

            System.out.println("< " + sort.getClass().getSimpleName() + " >");
            System.out.println("Original data: " + Arrays.toString(data));
            sort.run(data);
            System.out.println("Sorted data: " + Arrays.toString(data));
            System.out.println();
        }

    }
}
