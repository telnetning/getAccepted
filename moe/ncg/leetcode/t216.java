package moe.ncg.leetcode;

/*
 * 该题这里使用了位图来进行求组合从而遍历
 * 另一方面，本题可以直接使用深度搜索遍历求解
 */
import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;

public class t216 {
    static int[] mi = {1,2,4,8,16,32,64,128,256,512};
    public List<List<Integer>> combinationSum3(int k, int n) {
        int minSum = 0;
        int maxSum = 0;
        int minNum = 1;
        int maxNum = 9;
        LinkedList result = new LinkedList<LinkedList<Integer>>();
        
        //判断是否有解
        if(k > 9 || k < 1) return result;
        
        for(int i = 1; i <= k; i++) {
            minSum += i;
            maxSum += 10 - i;
        }
        
        if(n > maxSum || n < minSum) return result;
        
        //使用位图遍历
        maxNum = Math.min(n + k - minSum, 9);
        minNum = Math.max(n + 10 - k - maxSum, 1);
        
        int len = maxNum - minNum + 1;
        int bitmapRange = (int) Math.pow(2, len);
        
        int sum = 0;
        
        //得到位图的第一个数
        int m = 1;
        int firstNum = 1;
        for(int i = 1; i <= k - 1; i++) {
            firstNum += m << 1;
            m = m << 1;
        }
        
        int num = firstNum;  //num始终为二进制中1的个数为k个的数
        
        Integer[] intArr = new Integer[k];
        int index = 0;
        
        int[] weight= new int[len];  //下标权重
        for(int i = 0; i < len; i++) {
            weight[i] = minNum + i;
        }
        
        while(num <= bitmapRange) {
            for(int i = 0; i < len; i++) {
                if( (num & mi[i]) != 0) {
                    intArr[index++] = weight[i];
                    sum += weight[i];
                }
            }
            
            if(sum == n) {
                result.add(Arrays.asList(intArr.clone()));
            }
            num = getNext(num);
            index = 0;
            sum = 0;
        }
        
        return result;
    }
    
    //返回下一个和 k 具有相同 1 个数的数
    public static int getNext(int m) {
        int x = m & (-m);
        int t = m+x;
        return t | ((m^t)/x)>>2;
    }
}
