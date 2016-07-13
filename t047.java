/*
 * 去重复项的排列问题
 * 2016年 7月13日 星期三 22时11分44秒 CST
 */
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;

public class t047 {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        if(nums.length == 0) return null;
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length == 1) {
            res.add(intArrToList(nums)); 
            return res;
        }
        
        //nums[0] 作为第一个数字的时候
        for(List<Integer> l : permuteUnique(Arrays.copyOfRange(nums, 1, nums.length))) {
            l.add(0, nums[0]);
            res.add(l);
        }

        //与之后的并且和自己不相等的数字交换，若一个数字出现多次，则只交换一次
        HashSet<Integer> known = new HashSet<>();
        for(int i = 1; i < nums.length; i++) {
            if( (nums[i] != nums[0]) && (!known.contains(nums[i]))) {
                known.add(nums[i]);
                int[] numsTmp = nums.clone();
                numsTmp[0] = nums[i];
                numsTmp[i] = nums[0];
                
                for(List<Integer> l : permuteUnique(Arrays.copyOfRange(numsTmp, 1, numsTmp.length))) {
                    l.add(0, numsTmp[0]);
                    res.add(l);
                }
            }  //end if
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
        int[] arr = {2, 2, 1, 1};
        List<List<Integer>> res = permuteUnique(arr); 
        for(List<Integer> l : res) {
            System.out.print("新的列表：");        
            for(Integer i : l) {
                System.out.print(i + "\t"); 
            }
            System.out.println();
        }
    }
}
