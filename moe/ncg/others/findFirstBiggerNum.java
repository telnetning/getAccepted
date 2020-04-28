import java.util.Arrays;
import java.util.Stack;

/***
 * 有一个无序数组，找到每一个元素后面第一个比它大的数
 *
 *【单调栈】解决
 * 使用一个递减栈
 *
 * @author telnetning
 * @CreateAt Wed Mar 20 00:44:53 CST 2019
 */

public class findFirstBiggerNum
{
    public static void main(String[] args) {
        int[] input = {5, 1, 9, 8, 2};
        int[] result = solve(input);
        System.out.println(Arrays.toString(result));
    }

    public static int[] solve(int[] input) {
        Stack<Integer> decStack = new Stack<>();

        int[] result = new int[input.length];
        for(int i = 0; i < input.length; i++) {
            if (decStack.size() == 0) {
                decStack.push(i);
                continue;
            }

            while(!decStack.empty() && input[decStack.peek()] < input[i]) {
                result[decStack.pop()] = input[i];
            }

            decStack.push(i);
        }

        while(!decStack.empty()) {
            //右边没有比它大的数，设置成-1
            result[decStack.pop()] = -1;
        }

        return result;
    }

}
