/*
 * 2016年 5月25日 星期三 23时07分07秒 CST
 * 参看资料：
 * https://segmentfault.com/a/1190000003914228
 */
import java.util.Scanner;

public class t1032 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);         
        int N;
        N = Integer.parseInt(scanner.nextLine());
        char[] str;
        for(int i = 0; i < N; i++)
        {
            str = handleStr(scanner.nextLine().toCharArray());
            System.out.println(manacher(str));
        }
    }
    
    private static int manacher(char[] str) {
        int pos;  //maxRight对应的轴
        int maxRight;  //当前对称所能达到的极右
        int len = str.length;
        int RL[] = new int[len];  //回文半径数组
        int low, high;
        int s1, s2, dis;

        RL[0] = 1;  //最右到当前字符的距离，包括首尾
        pos = 0;
        maxRight = 0;
        
        //计算以i为中心的RL[i]
        for(int i = 1; i < len - 1; i++)
        {
            //设置起始遍历位置
            if(i >= maxRight)
            {
                low = i - 1;
                high = i + 1;
            } else {
                s1 = maxRight - i; 
                s2 = RL[2 * pos - i];
                dis = s1 > s2 ? s2 : s1; 
                low = i - dis + 1;
                high = i + dis - 1;
            }

            //开始遍历  
            while( (low >= 0) && (high < len) )
            {
                //当恰好不满足的时候退出
                if(str[low] == str[high])     
                {
                    low--;
                    high++;
                } else {
                    break; 
                }
            }

            //更新 pos 和 maxRight
            if(high > maxRight)
            {
                pos = i;
                maxRight = high - 1;  //最后满足的地方
            }

            //更新RL
            RL[i] = high - i;
        }  //end for
        
        RL[len - 1] = 1;
        
        /*
        for(int i = 0; i < len; i++){
            System.out.println(RL[i]);
        }
        */

        int maxLen = 0;
        for(int i = 0; i < len; i++)
        {
            /* RL[i]-1的值，正是在原本那个没有插入过分隔符的串中，
             * 以位置i为对称轴的最长回文串的长度
             * 可以从对称轴为分隔符和非分隔符两种形式进行观察
             */
            maxLen = maxLen > RL[i] - 1 ? maxLen : RL[i] - 1;    
        }
        
        return maxLen;
    }
    
    //使用插入字符法，解决奇偶性带来的对称轴不一致的问题
    private static char[] handleStr(char[] str) {
        //在字符串中的所有间隔（包括最左和最右）添加一个符号    
        int len = str.length;
        char[] newStr = new char[2 * len + 1];
        for(int i = 0; i < 2 * len + 1; i += 2)
        {
            newStr[i] = '#';   
        }

        for(int i = 0; i < len; i++) 
        {
            newStr[i * 2 + 1] = str[i];
        }

        return newStr;
    }

}
