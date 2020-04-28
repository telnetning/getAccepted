import java.util.Arrays;
public class t327 {
    public static void main(String[] args) {
        int[] arr = {-2147483647, 0, -2147483647, 2147483647};     

        System.out.println(countRangeSum(arr, -2, 2));
    }

    public static int countRangeSum(int[] nums, int lower, int upper) {
        //preparCal
        long[] sums = new long[nums.length];
        for(int i = 0; i < nums.length; i++) {
            if(i == 0) 
            {
                sums[0] = nums[0];
            } else {
                sums[i] = (long)nums[i] + (long)sums[i - 1];    
            }              
        }  //end for
        
        System.out.println(Arrays.toString(sums));
        int count = 0;  

        for(int i = 0; i < sums.length; i++) {
            for(int j = i; j < sums.length; j++) {
                if(i == 0) {
                    if(sums[j] >= lower && sums[j] <= upper) 
                    {
                        count++;
                    } 
                } else {
                    long diff = sums[j] - sums[i - 1];
                    if(diff >= lower && diff <= upper) 
                    {
                        count++;
                    } 
                }           
            }  //end for
        }  //end for  

        return count;
    }
}
