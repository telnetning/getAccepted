package moe.ncg.leetcode;

/**
 * Created by telnetning on 16/8/25. 
    这一题用HashMap大材小用，而且涉及到包装类，消耗较大
    讨论区的一个方法，用int数组，字母作为对应下标，
    比如 bb ff
    int[128] arr1
    int[128] arr2
    那么arr1[b] arr2[f] 的值设置为相等，比如设置成当前遍历数 i
 */
import java.util.*;
public class t205 {
    public static void main(String[] args) {
        Solution205 S = new Solution205();
        String s = "ab";
        String t = "aa";
        boolean b = S.isIsomorphic(s, t);
        System.out.println(b);
    }
}

class Solution205 {
    public boolean isIsomorphic(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen != tLen) return false;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        HashMap h = new HashMap<Character, Character>();
        HashMap hr = new HashMap<Character, Character>();

        for(int i = 0; i < sLen; i++) {
            if(h.containsKey(sArr[i]) || hr.containsKey(tArr[i])) {
                if((char)h.get(sArr[i]) != tArr[i])  return false;
                if((char)hr.get(tArr[i]) != sArr[i]) return false;
            } else {
                h.put(sArr[i], tArr[i]);
                hr.put(tArr[i], sArr[i]);
            }
        }
        return true;
    }
}