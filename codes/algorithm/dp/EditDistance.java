package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class EditDistance {

    public int run(String word1, String word2) {
        // Store length of words
        int len1 = word1.length();
        int len2 = word2.length();

        // Init array for memoization
        int[][] distance = new int[len1 + 1][len2 + 1]; // One space for easy implement
        for (int i = 0; i <= len1; i++) {
            distance[i][0] = i; // edit distance(word1, "")
        }
        for (int i = 0; i <= len2; i++) {
            distance[0][i] = i; // edit distance("", word2)
        }

        // Compute distance
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = Math.min(distance[i - 1][j - 1], Math.min(distance[i - 1][j], distance[i][j - 1])) + 1;
                }

            }

        }

        return distance[len1][len2]; // Original solution
    }


    public static void main(String[] args) throws IOException {
        // Read two words
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter target words: ");
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // Run
        System.out.println(new EditDistance().run(st.nextToken(), st.nextToken()));

        br.close();
    }
}
