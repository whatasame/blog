package codeup.basic100.from1010to1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1014 {

    /*
    * https://codeup.kr/problem.php?id=1014
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        char c1 = st.nextToken().charAt(0);
        char c2 = st.nextToken().charAt(0);

        System.out.println(c2 + " " + c1);

        br.close();
    }
}
