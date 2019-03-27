import java.util.Stack;

/**
 * leetcode T042: Trapping Rain Water
 * 使用单调栈解决雨水收集问题
 *
 * 使用 Stack<Integer> 逻辑更清晰但是耗时和资源更多
 * 最快最优的解法应该是直接在 height 数组上原地操作计算
 *
 * @author telnetning
 * @CreateAt Wed Mar 27 20:26:19 CST 2019
 */

public class trappingRainWater
{
    public static void main(String[] args) {
        int[] arr01 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(arr01));
    }

    public static int trap(int[] height) {
        Stack<Integer> descStack = new Stack<>();
        int result = 0;

        for(int i = 0; i < height.length; i++) {
            if(descStack.empty()) {
                descStack.push(i);
                continue;
            }

            while(!descStack.empty() && height[i] > height[descStack.peek()] ) {
                int curNum = descStack.pop();
                if(!descStack.empty()) {
                    result += (Math.min(height[i], height[descStack.peek()])
                        - height[curNum]) * (i - descStack.peek() - 1);
                    System.out.println(result);
                }
            }

            descStack.push(i);
        }

        return result;
    }
}
