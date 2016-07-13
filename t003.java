/*
 * 求解字符串中最大的不含有重复字符的子串
 * 最终解法类似于 KMP ，根据上一次的匹配结果来确定下一次的匹配起始位置
 */
import java.util.Hashtable;

public class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int index = 0;
        int beginIndex = 0;
        int longest = 0;
        int currentLong = 0;
        int nextBegin = 0;
        char[] arr = s.toCharArray();
        int len = arr.length;
        if(len == 1) return 1;
        Hashtable<Character, Integer> used =
        new Hashtable<Character, Integer>();
        
        while(index < len) {
            if(used.get(arr[index]) == null) {
                used.put(arr[index], index);  //store the index of character
                currentLong = index - beginIndex + 1;
                longest = longest > currentLong ? longest : currentLong;
                index++;
            } else {
                currentLong = index - beginIndex;
                longest = longest > currentLong ? longest : currentLong;
                nextBegin = used.get(arr[index]) + 1;  //next time begin character index
                for(int i = beginIndex; i < nextBegin - 1; i++) {
                    used.remove(arr[i]);
                }
                used.put(arr[index], index);
                beginIndex = nextBegin;
                index++;
            }
        }
        return longest;
    }
}
