import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Stack;
import java.lang.String;

public class t241 {
    public static List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<Integer>();
        char[] inputArr = input.toCharArray();
        String[] s = input.split("\\*|-|\\+");
        if(s.length == 1) {
            res.add(Integer.parseInt(input));
            return res;
        }

        int numsLen = s.length; 
        int opeNum = numsLen - 1;
        int[] ope = new int[opeNum];
        int[] nums = new int[numsLen];
            
        for(int i = 0; i < s.length; i++) {
            nums[i] = Integer.parseInt(s[i]);
        }

        int index = 0;
        for(int i = 0; i < input.length(); i++) {
            if(inputArr[i] == '*' | inputArr[i] == '+' | inputArr[i] == '-') {
                ope[index++] = inputArr[i];    
            }
        } 
        System.out.println(Arrays.toString(nums)); 
        System.out.println(Arrays.toString(ope));
        System.out.println(Arrays.toString(s));

        int[] permu = new int[opeNum];     
        for(int i = 0; i < opeNum; i++) {
            permu[i] = i;
        }

        List<List<Integer>> allPermu = calPer(permu);
        for(List<Integer> l : allPermu) {
            int[] order = new int[l.size()];
            for(int i = 0; i < order.length; i++) {
                order[i] = l.get(i);
            }
            res.add(calculate(nums, ope, order));
        }

        return res;
    }
    

    public static int calculate(int[] nums, int[] opeArr, int[] order) {
        List<Integer> lNums = intArrToList(nums);  
        List<Integer> lOpeArr = intArrToList(opeArr);
        List<Integer> lOrder = intArrToList(order);
        int len = order.length;
        int num1;
        int num2;
        for(int i = 0; i < len; i++) {
            int j = 0;
            for(j = 0; j < len ; j++) {
                if(lOrder.get(j) == i) break; 
            } 
            
            num1 = lNums.remove(j + 1);
            num2 = lNums.remove(j);
            lNums.add(j, calOpe(num2, lOpeArr.get(j), num1));
            lOrder.remove(j); 
            lOpeArr.remove(j);
        }

        return lNums.remove(0);
    }

    public static int calOpe(int num1, int ope, int num2) {
       
        if(ope == 45) return num1 - num2;        
        if(ope == 43) return num1 + num2;
        if(ope == 42) return num1 * num2;
        return -1000000;
    }

    public static List<List<Integer>> calPer(int[] permu)  {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if(permu.length == 1) {
            //得到一组排列     
            res.add(intArrToList(permu));
            return res;    
        } 
        
        for(List<Integer> l : calPer(Arrays.copyOfRange(permu, 1, permu.length))) {
            l.add(0, permu[0]); 
            res.add(l);
        } 
        
        for(int i = 1; i < permu.length; i++) {
            int[] permuTmp = permu.clone();
            permuTmp[0] = permu[i];
            permuTmp[i] = permu[0];
            
            for(List<Integer> l : calPer(Arrays.copyOfRange(permuTmp, 1, permuTmp.length))) {
                l.add(0, permuTmp[0]);
                res.add(l);
            }
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
        String s = "2*3-4*5";    
        List<Integer> res = diffWaysToCompute(s);
        res.forEach(System.out::println);  
    }
}
