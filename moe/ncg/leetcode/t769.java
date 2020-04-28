package moe.ncg.leetcode;

/***
 * 根据数组下标与数组中的数字的大小关系处理
 *
 * @author telnetning
 * @CreateAt Sun Mar 24 23:05:08 CST 2019
 */

public class t769
{

    public static void main(String[] args) {
        int[] arr01 = {4, 3, 2, 1, 0};
        System.out.println(maxChunksToSorted(arr01));
        int[] arr02 = {1,0,2,3,4};
        System.out.println(maxChunksToSorted(arr02));
    }

    public static int maxChunksToSorted(int[] arr) {
        int result = 0;
        int len = arr.length;
        int curMax = 0;

        for(int i = 0; i < len; i++) {
            curMax = Math.max(curMax, arr[i]);
            if (i == curMax) {
                result += 1;
            }
        }
        return result;
    }

}
