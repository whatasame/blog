package codeup.basic100.from1010to1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1018 {

    /*
    * https://codeup.kr/problem.php?id=1018
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), ":");
        String hour = st.nextToken();
        String minute = st.nextToken();

        System.out.println(hour + ":" + minute);

        br.close();
    }
}
