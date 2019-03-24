import java.util.Stack;

/***
 * 数组中的数是任意整数
 *
 * 【单调栈】
 *  使用单调栈，维护一个递增栈
 *  保证栈顶的元素始终是遍历到目前为止最大的元素
 *
 *  栈中的元素个数表示的即为当前的 chunk 个数
 *  每一个元素都是实际上的一个区域峰值
 *
 *  每当拿到一个比栈顶小的数的时候，都去往前遍历栈，查看哪些
 *  先前认为是合理 chunk 的现在被推翻
 *
 * @author telneting
 * @CreatedAt Mon Mar 25 00:03:21 CST 2019
 */
public class MaxChunksToMakeSortedII
{
    public static void main(String[] args) {
        int[] arr01 = {1,0,3,3,2};
        System.out.println(maxChunksToMakeSort(arr01));
        int[] arr02 = {2,1,3,4,4};
        System.out.println(maxChunksToMakeSort(arr02));
    }

    public static int maxChunksToMakeSort(int[] arr) {
        Stack<Integer> ascStack = new Stack<>();

        int curMax = 0;
        for(int i = 0; i < arr.length; i++) {
            if (ascStack.empty()) {
                ascStack.push(arr[i]);
                curMax = arr[i];
            } else {
                if(ascStack.peek() <= arr[i]) {
                    ascStack.push(arr[i]);
                    curMax = arr[i];
                    continue;
                } else {
                    while(!ascStack.empty() && ascStack.peek() > arr[i]) ascStack.pop();
                    ascStack.push(curMax);
                }
            }
        }
        return ascStack.size();
    }
}
