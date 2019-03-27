import java.util.Stack;

/**
 * leetcode t084: Largest Rectangle in Histogram
 *
 * 单调栈
 *
 * 递增栈，每一个元素出栈时，计算以其为高度包含该块的面积
 * 计算公式：(此次出栈的最大元素index - 该元素的前一个元素的index） * 该元素高度
 * 或者：
 * （后一个高度比当前低的index - 前一个高度比当前低的index - 1）* 当前块的高度
 *
 * @author telnetning
 * @CreateAt Thu Mar 28 00:19:56 CST 2019
 */
public class LargestRectangleInHistogram
{
    public static void main(String args[]) {
        int[] arr01 = {4, 2, 0, 3, 2, 5};
        int[] arr02 = {2, 1, 2};
        int[] arr03 = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(arr01));
        System.out.println(largestRectangleArea(arr02));
        System.out.println(largestRectangleArea(arr03));

    }

    public static int cal(Stack<Integer> descStack, int[] heights, int curMaxIndex) {
        int curIndex = descStack.pop();
        int preIndex = descStack.empty() ? -1 : descStack.peek();
        return (curMaxIndex - preIndex) * heights[curIndex];
    }

    public static int largestRectangleArea(int[] heights) {
        int result = 0;
        Stack<Integer> descStack = new Stack<>();
        int curMaxIndex = 0;

        for(int i = 0; i < heights.length; i++) {

            if(descStack.empty())  {
                descStack.push(i);
            }

            //递增栈逻辑
            if(heights[i] < heights[descStack.peek()]) {
                curMaxIndex = descStack.peek();
                while(!descStack.empty() && heights[descStack.peek()] >= heights[i]) {
                    result = Math.max(result, cal(descStack, heights, curMaxIndex));
                }
            }

            descStack.push(i);

        }

        //处理最后descStack中剩余的
        if(!descStack.empty()) {
            curMaxIndex = descStack.peek();
        }

        while(!descStack.empty()) {
            result = Math.max(result, cal(descStack, heights, curMaxIndex));
        }

        return result;
    }
}
