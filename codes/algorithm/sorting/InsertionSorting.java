package algorithm.sorting;

import java.util.ArrayList;
import java.util.List;

public class InsertionSorting {

    public static void sort(List<Integer> targetList) {

        for (int i = 0; i < targetList.size(); i++) {
            int j;
            Integer target = targetList.get(i);
            for (j = i; j >= 0; j--) {
                if(j == i){
                    continue;
                }
                if(targetList.get(j) > target){
                    targetList.set(j+1, targetList.get(j));
                }
                else{
                    break;
                }
            }
            targetList.set(j + 1, target);
        }

    }

    public static void main(String[] args) {
        List<Integer> testCase = new ArrayList<Integer>() {
            {
                add(999);
                add(1);
                add(5);
                add(4);
                add(2);
                add(3);
            }
        };

//        for (Integer data : testCase) {
//            System.out.println(data);
//        }

        sort(testCase);


        for (Integer data : testCase) {
            System.out.print(data + " ");
        }
    }

}
