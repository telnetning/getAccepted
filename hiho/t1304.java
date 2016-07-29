import java.util.Scanner;
import java.lang.Math;
import java.lang.String;

public class t1304 {
    static int[] nowOpe = new int[3];  //当前使用的运算符
    //static int[] opeType = new int[6];  //一共六种运算符
    static boolean[] used = new boolean[4]; 
    static int nowNumber[] = new int[4];  //当前使用的数字
    static int[] number = new int[4];  //用户输入的数字
    /*
     * 六种运算符分别定义为：
     * +, -, x, /, 被-，被/
     */ 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for(int i =0 ; i < 4; i++) 
        {
            used[i] = false;
        }
        String[] sArray = new String[4];       
        int N = Integer.parseInt(scanner.nextLine());
        for(int i = 0; i < N; i++)
        {
            sArray = scanner.nextLine().split(" ");     
            
            for(int k =0 ; k < 4; k++) 
            {
                used[k] = false;
            }
            for(int j = 0; j < 4; j++)
            {
                number[j] = Integer.parseInt(sArray[j]);
            }
            
            /*
            for(int j = 0; j < 4; j++)
            {
                System.out.println(number[j]); 
            }
            */
            System.out.println(makeNumber(0) ? "Yes" : "No");
        }

    }

    private static boolean makeNumber(int depth) {
        if(depth >= 4)  //排好一个数字序列                        
        {
            return makeOpe(0); 
        }

        for(int i = 0; i < 4; i++)
        {
            if(!used[i])    //每个数字只能使用一次 
            {
                nowNumber[depth] = number[i];  //已有列表中加入该数字
                used[i] = true;
                if(makeNumber(depth + 1))
                {
                    return true; 
                }
                used[i] = false;  //已有列表中拿掉该数字，依据i+1进行下一次计算
            }
        }

        return false;
    }

    private static boolean makeOpe(int depth) {
        if(depth >= 3)  //已经排好一个系列的运算符号
        {
            if(calType1()) return true;
            if(calType2()) return true;
            return false;
        }

        for(int i = 0; i < 6; i++)
        {
            nowOpe[depth] = i;                
            if(makeOpe(depth + 1)) return true;
        }

        return false;
    }
    
    private static boolean calType1() {
        float result;                         
        try{
            //以防出现 divide 为 0 的情况
            result = useOpe(nowNumber[0], nowNumber[1], nowOpe[0]); 
            result = useOpe(result, nowNumber[2], nowOpe[1]);
            result = useOpe(result, nowNumber[3], nowOpe[2]);
        } catch(Exception e) {
            return false; 
        }
        if(Math.abs(result - 24.0) < 0.01) return true;
        return false;
    }

    private static boolean calType2() {
        float result1;
        float result2;
        float result;
        
        try{
            result1 = useOpe(nowNumber[0], nowNumber[1], nowOpe[0]);
            result2 = useOpe(nowNumber[2], nowNumber[3], nowOpe[2]);
            result = useOpe(result1, result2, nowOpe[1]);
        } catch(Exception e) {
            return false; 
        }

        if(Math.abs(result - 24.0) < 0.01) return true;
        return false;
    }
    
    private static float useOpe(float a, float b, int ope){
        if(ope == 0) return a + b;
        if(ope == 1) return a - b;
        if(ope == 2) return a * b;
        if(ope == 3) return a / b;
        if(ope == 4) return b - a;
        if(ope == 5) return b / a;
        return 0;
    }
}
