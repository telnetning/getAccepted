package moe.ncg.leetcode;

/**
 * Created by telnetning on 16/8/5.
 */
import java.util.*;

public class t119 {
    public static void main(String[] args)
    {
        List<Integer> row = new ArrayList<>();
        SolutionE s = new SolutionE();
        int k = 6;
        row = s.getRow(k);
        for(Integer e : row)
        {
            System.out.println(e);
        }
    }
}

class SolutionE {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        //System.out.println(Arrays.toString(getRowArr(rowIndex)));
        for(Integer e : getRowArr(rowIndex))
        {
           row.add(e);
        }
        return row;
    }

    public int[] getRowArr(int rowIndex)
    {
        int[] arr = new int[rowIndex + 1];
        arr[0] = 1;
        if(rowIndex == 0) return arr;
        int i = 0;
        int pre;
        int cur;
        while(i++ < rowIndex)
        {
            cur = 1;
            for(int j = 1; j < rowIndex; j++)
            {
                pre = cur;
                cur = arr[j];
                arr[j] += pre;
            }
            arr[rowIndex] = 1;
        }
        return arr;
    }
}
