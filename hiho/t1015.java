/*
 * 2016年 5月25日 星期三 18时19分55秒 CST
 * 参考资料：
 * http://www.cnblogs.com/c-cloud/p/3224788.html
 * http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
 */

import java.util.Scanner;

public class t1015 {
    public static void main(String[] args)
    {
        
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        //System.out.println(N);
        int i = 0;
        int m, res;
        int[] next;         
        while(i < N){
            char[] par = scanner.nextLine().toCharArray();
            char[] ori = scanner.nextLine().toCharArray();
            m = par.length;
            //System.out.println(m);
            next = new int[m];
            next = makeNext(par);
        
            res = kmp(ori, par, next);
        
            System.out.println(res);
            i++;
        }
    }

    private static int kmp(char[] ori, char[] par, int[] next)
    {
        int oriLen = ori.length;
        int parLen = par.length;
        
        int count = 0;
        int k_o, k_p; //分别表示 ori 下标和 par 下标  
        k_o = 0;
        k_p = 0;
        while(k_o < oriLen)
        {
            //System.out.println(k_o + "\t" + k_p);
            if(ori[k_o] == par[k_p])
            {
                k_o++;
                k_p++;
            } else {
                if(k_p == 0)
                {
                    k_o++;  //如果par已经是在拿首字符进行匹配没匹配上
                } else {
                    k_p = next[k_p - 1];  //移动之后，前面的 next[k_p-1] 长度可以自然匹配
                }
            }

            if(k_p == parLen)
            {
                //若成功匹配了,则准备下一次匹配
                count++;
                k_p = next[parLen - 1];
            }
        }

        return count;
    }

    private static int[] makeNext(char[] param)
    {
        int q, k;  
        int m = param.length;
        int[] next = new int[m];
        next[0] = 0;
        k = 0;  //代表上一次计算得到的最大相同前后缀的长度
        for(q = 1; q < m; q++)
        {
            while(k > 0 && param[q] != param[k])  //原串增长param[q], 查看是否可以刚好匹配  
                k = next[k - 1];  

            if(param[q] == param[k]) 
                k++;

            next[q] = k;
        }

        return next;
    }
}
