/**
 * Created by telnetning on 16/8/6.
 */
import java.util.*;

public class t150 {
    public static void main(String[] args)
    {
        Solution1 s = new Solution1();
        String[] str = new String[] {"2", "10", "+", "3", "*"};
        System.out.println(s.evalRPN(str));
    }
}

class Solution1 {
    public int evalRPN(String[] tokens) {
        int res;
        Deque<String> stack = new ArrayDeque<String>();
        int opeNum1;
        int opeNum2;
        for(String s : tokens)
        {
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
            {
                opeNum2 = Integer.parseInt(stack.removeFirst());
                opeNum1 = Integer.parseInt(stack.removeFirst());
                stack.addFirst(String.valueOf(cal(opeNum1, opeNum2, s)));
            } else {
                stack.addFirst(s);
            }

        }

        return Integer.parseInt(stack.removeFirst());
    }

    public int cal(int num1, int num2, String s)
    {
        if(s.equals("+")) return num1 + num2;
        if(s.equals("-")) return num1 - num2;
        if(s.equals("/")) return num1 / num2;
        if(s.equals("*")) return num1 * num2;

        return -1000000;
    }
}