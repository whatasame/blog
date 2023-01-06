package algorithm.peakfinder;

public class PeakFinder2D {

    public static String toStringData(int[][] data) {

        /* Return String representing value of two dimension array */

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            sb.append('[');
            for (int j = 0; j < data.length; j++) {
                sb.append(data[i][j]);
                if (j == data.length - 1) {
                    break;
                }
                sb.append(", ");
            }
            sb.append(']').append('\n');
        }

        return sb.toString();
    }

    private static int[] find(int[][] data) {

        /* Return peak index {row, col} */

        int targetRow = 0, targetColumn = 0; // peak: (targetRow, targetColumn)
        int leftColumn = 0, rightColumn = data.length - 1; // Range of subarray

        while (leftColumn < rightColumn) {
            // Calculate middle column index of array
            targetColumn = leftColumn + (rightColumn - leftColumn) / 2;

            // Find index of maximum value in column
            targetRow = getMaxValueRowIndex(data, targetColumn);

            // Compare to adjacent columns
            if (data[targetRow][targetColumn] < data[targetRow][targetColumn + 1]) {
                leftColumn = targetColumn + 1;
            } else {
                rightColumn = targetColumn;
            }
        }

        return new int[]{targetRow, targetColumn};
    }

    private static int getMaxValueRowIndex(int[][] data, int middleColumn) {

        // Index of max value in column
        int index = 0;

        // Row count = data.length
        for (int i = 1; i < data.length; i++) {
            if (data[i][middleColumn] > data[index][middleColumn]) {
                index = i;
            }
        }

        return index;
    }


    public static void main(String[] args) {

        // Generate random test case: N x N matrix
        int N = 5;
        int[][] data = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                data[i][j] = (int) (Math.random() * N * N + 1);
            }
        }

        // Print original data
        System.out.println(toStringData(data));

        // Find peak
        int[] peakIndex = find(data);
        int row = peakIndex[0];
        int col = peakIndex[1];
        System.out.printf("Peak is %d (row: %d, col: %d)", data[row][col], row, col);
    }
}
