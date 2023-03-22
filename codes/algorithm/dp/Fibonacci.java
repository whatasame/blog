package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;

public class Fibonacci {

    public static BigInteger dp(int N) {
        // Init values
        BigInteger[] data = new BigInteger[N + 1];
        Arrays.fill(data, BigInteger.ONE);
        data[0] = new BigInteger("0");
        data[1] = new BigInteger("1");

        // Run dp
        for (int i = 2; i < data.length; i++) {
            data[i] = data[i - 1].add(data[i - 2]);
        }

        // Return Nth value of fibonacci
        return data[N];
    }

    public static BigInteger recursive(int N) {
        BigInteger num = new BigInteger(String.valueOf(N));

        // Check end condition
        int compare = num.compareTo(new BigInteger("2"));
        if (compare < 0) { // num < 2
            return num;
        }

        // Recursive
        return recursive(N - 1).add(recursive(N - 2));
    }

    public static void main(String[] args) throws IOException {
        // Read data count
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the number of data: ");
        int N = Integer.parseInt(br.readLine());

        // Run by dp
        long start = System.currentTimeMillis();
        System.out.println(dp(N));
        System.out.println("DP: " + (System.currentTimeMillis() - start) + "ms");

        // Run by recursive
        start = System.currentTimeMillis();
        System.out.println(recursive(N));
        System.out.println("Recursive: " + (System.currentTimeMillis() - start) + "ms");

        br.close();
    }

}
