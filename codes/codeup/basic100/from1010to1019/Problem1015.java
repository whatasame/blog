package codeup.basic100.from1010to1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1015 {

    /*
    * https://codeup.kr/problem.php?id=1015
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        float f = Float.parseFloat(br.readLine());

        System.out.printf("%.2f", f);

        br.close();
    }
}
