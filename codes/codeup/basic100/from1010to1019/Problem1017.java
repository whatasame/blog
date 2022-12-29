package codeup.basic100.from1010to1019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1017 {

    /*
    * https://codeup.kr/problem.php?id=1017
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        System.out.println(num + " " + num + " " + num);

        br.close();
    }

}
