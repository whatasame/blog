package codeup.basic100.from1010to1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1012 {

    /*
    * https://codeup.kr/problem.php?id=1012
    *
    * System.out.printf()를 통해 소수점 아랫자리를 padding 하자.
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        float f = Float.parseFloat(br.readLine());

        System.out.printf("%f", f);

        br.close();
    }
}
