/*
 * 该题的一个需要注意的地方是，将 String 替换成 StringBuilder 能大大提高效率
 */
public class Solution {
    public String reverseWords(String s) {
        StringBuilder rs = new StringBuilder();
        String[] sArr = s.split(" ");
        int len = sArr.length;
        for(int i = len - 1; i >= 0; i--) {
            if(i != len - 1 && sArr[i].toCharArray().length != 0) {
                rs.append(" ");
            }
            
            rs.append(sArr[i]);
        }
        return rs.toString();
    }
}
