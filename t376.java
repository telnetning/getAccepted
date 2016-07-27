import java.util.Arrays;

class Solution {
    public int wiggleMaxLength(int[] nums) {
        int arrLen = nums.length;
        if(arrLen <= 1) return arrLen;
        if(arrLen == 2 && nums[0] == nums[1]) return 1;
        if(arrLen == 2) return 2;
        int[] diff = new int[arrLen - 1];
        
        for(int i = 0; i < arrLen - 1; i++)
        {
            diff[i] = nums[i + 1] - nums[i];
        }
        
        System.out.println(Arrays.toString(diff));

        int count = arrLen - 1;
        int cur = 0;
        
        //find the first diff not equals zero
        while(cur < arrLen - 1)
        {
            if(diff[cur] == 0) {
                cur++;
                count--;
                continue;
            }
            break;
        }
        
        int next = cur + 1;
        
        while(cur < arrLen - 1 && next < arrLen - 1)
        {
            if(diff[cur] * diff[next] < 0)
            {
                cur = next;
                next++;
            } else {
                count--;
                next++;
            }
        }
        
        return count + 1;
    }
}

public class t376 {
    public static void main(String[] args) {
        int[] arr = {33,53,12,64,50,41,45,21,97,35,47,92,39,0,93,55,40,46,69,42,6,95,51,68,72,9,32,84,34,64,6,2,26,98,3,43,30,60,3,68,82,9,97,19,27,98,99,4,30,96,37,9,78,43,64,4,65,30,84,90,87,64,18,50,60,1,40,32,48,50,76,100,57,29,63,53,46,57,93,98,42,80,82,9,41,55,69,84,82,79,30,79,18,97,67,23,52,38,74,15}; 
        Solution s = new Solution();
        int res = s.wiggleMaxLength(arr);
        System.out.println("Result is: " + res);
    }
}
