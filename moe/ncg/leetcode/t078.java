package moe.ncg.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Math;

public class t078 {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();        

        int len = nums.length;
        if(len == 0) return res;
        
        int[] contrastArr = new int[len];
        for(int i = 0; i < len; i++) {
            contrastArr[i] = (int)Math.pow(2, i);    
        }
        //System.out.println(Arrays.toString(contrastArr));
        //使用位图遍历
        int maxNum = (int)Math.pow(2, len); 
        char[] binChar = new char[maxNum];

        List<Integer> permu = new ArrayList<Integer>();

        for(int i = 0; i < maxNum; i++ ) {
            /*
            binChar = Integer.toBinaryString(i).toCharArray();     
            for(int j = 0; j < binChar.length; j++) {
                if(binChar[j] == '1')  permu.add(nums[j + len - binChar.length]); 
            } 
            */
            for(int j = 0; j < len ; j++) {
                if( (i & contrastArr[j]) != 0) {
                    permu.add(nums[j]); 
                } 
            } 
            //List<Integer> newList = permu;  //不行
            //res.add(permu.clone());  //不行
            
            res.add(new ArrayList<Integer>(permu));
            permu.clear();
        }

        return res;
    }

    public static  List<Integer> intArrToList(int[] nums) {
            List<Integer> intList = new ArrayList<Integer>();
            for (int index = 0; index < nums.length; index++)
            {
                intList.add(nums[index]);
            }
        return intList;
    }

    public static void main(String[] args) {
        int[] input = {1, 3, 4};         
        List<List<Integer>> s = subsets(input);
        for(List<Integer> l : s) {
            System.out.println(Arrays.toString(l.toArray()));
        } 
    }    
}
