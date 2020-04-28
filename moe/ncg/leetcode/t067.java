package moe.ncg.leetcode;

/*
 * 2016年 7月13日 星期三 16时21分07秒 CST
 * 二进制数的加法
 * 由于二进制串的长度不定，所以想转换成 int 之后计算是不可行的
 */
public class t067 {
    public static void main(String[] args) {
        String a = "0";
        String b = "1";
            
        int lenA = a.length();
        int lenB = b.length();

        int maxLen = Math.max(lenA, lenB) + 1;
        boolean flag = false;

        for(int i = 1; i <= maxLen - lenA; i++) {
            a = "0" + a; 
        }

        for(int i = 1; i <= maxLen - lenB; i++) {
            b = "0" + b; 
        }
        
        char[] charA = a.toCharArray();
        char[] charB = b.toCharArray();
        char[] charC = new char[maxLen];
        for(int i = maxLen - 1; i >= 0; i--) {
            if(charA[i] == charB[i]) {
                charC[i] = flag ? '1' : '0';   
                flag = (charA[i] == '0') ? false : true;
            } else {
                charC[i] = flag ? '0' : '1'; 

                flag = flag ? true : false; 
            }                
        }  //end for

//        if(char[0] == '0') return (new String(charC)).substring(1);
//        return new String(charC);
    }
}
