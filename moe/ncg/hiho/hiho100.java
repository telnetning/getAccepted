/*
 * 八数码问题
 * 参考资料：
 * http://santong.me/2015/03/19/%E5%85%AB%E6%95%B0%E7%A0%81%E9%97%AE%E9%A2%98/
 * 一些需要注意的问题：
 * 1.java 中优先队列的使用，是不是高效，某种操作是不是需要遍历
 * 2.ArrayList 和LinkedList 的选用，需要根据实际情况
 * 3.equals 和 hashCode 方法的重写，equals 方法第一行判断参量类型不能舍去
 * 4.逆序数简化问题
 * 5.状态空间的压缩
 * 6.某些情况为了快速找到特定元素，多考虑 hash
 *2016年 6月 5日 星期日 10时36分59秒 CST
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.io.FileInputStream;

public class hiho100 {
    static int len = 9;        
    public static void main(String[] args) throws Exception
    {
        //FileInputStream fis = new FileInputStream("./hiho100.data");
        //System.setIn(fis);
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        //int N = 1;
        int[] arr = new int[9];
        //int[] arr = {7, 8, 4, 3, 5, 6, 1, 0, 2};
        int result;
        for(int i = 0; i < N; i++) 
        {
            for(int j = 0; j < 9; j++)     
            {
                arr[j] = scanner.nextInt(); 
            }
            //int[] arr = {0, 5, 4, 3 , 8, 2, 1, 6, 7};
            ThreeOrderStatus s = new ThreeOrderStatus(9, arr);
            if(!s.ifSolution())
            {
                System.out.println("No Solution!");      
                continue;
            }
            System.out.println(s.search());
        }
    } //end main
}

class Status {
    int num;  //该状态在所有状态中的排序
    int len;
    int[] arrange = new int[len];  //该状态的全排列

    public Status(int len, int num) 
    {
        this.num = num;
        this.len = len;
        this.arrange = reCantor(num);
    }
    
    public Status(int len, int[] arrange)       
    {
        this.arrange = arrange;         
        this.len = len;
        this.num = cantor(arrange);
    }
    
    protected int cantor() {
        return cantor(this.arrange); 
    }
        
    protected int[] reCantor() {
        return reCantor(this.num); 
    }
    protected int cantor(int[] arrange)
    {
        int X = 0;
        int tp;
        int len = arrange.length;                                 
        for(int i = 0; i < len; i++) 
        {
            tp = 0;
            for(int j = i + 1; j < len; j++)
            {
                if(arrange[j] < arrange[i]) tp++;
            }
            X += tp * factorial(len - i - 1);
        }

        return X;
    }
    
    private int factorial(int j)
    {
        if(j == 0) return 1;
        int sum = 1;
        for(int i = j; i > 0; i--) sum *= i;    
        return sum;
    }

    protected int[] reCantor(int num)
    {
        int len = this.len;
        int X = num;
        int cnt;
        int[] arrange = new int[len];                                  
        boolean[] used = new boolean[len];
        for(int i = 0; i < len; i++) used[i] = false;
             
        for(int i = 0; i < len; i++)
        {
            arrange[i] = X / factorial(len - 1 - i);
            X = X % factorial(len - 1 -i);
            cnt = 0;
            for(int j = 0; j < len; j++)
            {
                if(!used[j]) 
                {
                    cnt += 1;      
                    if(cnt == arrange[i] + 1) 
                    {
                        arrange[i] = j;
                        used[j] = true;
                        break;
                    }
                }
            }
        }

        return arrange;
    }

    public int getNum()
    {
        return this.num;      
    }
    
    public int[] getArrange() 
    {
        return this.arrange; 
    }
}

class ThreeOrderStatus extends Status {
    int g;  //实际步数 
    int h;  //估值函数
    int f;  //总和
    static int[] lastStatus = {1,2,3,4,5,6,7,8,0};  //最终状态
    
    public boolean ifSolution()
    {
        int cnt = 0;
        for(int i = 0; i < 9; i++)             
        {
            if(arrange[i] == 0) continue;  //空白格不影响逆序数奇偶性
            for(int j = 0; j < i; j++) 
            {
                if(arrange[j] > arrange[i]) cnt++;             
            }
        }
        if(cnt % 2 == 0) return true;
        return false;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof ThreeOrderStatus)) return false;
        if(o == null) return false;
        ThreeOrderStatus e = (ThreeOrderStatus) o;
        if(this.num != e.num) return false;
        return true;
    }
    
    public int hashCode()
    {
        return this.num;
    }

    public ThreeOrderStatus(int len, int num)            
    {
        super(len, num); 
    }

    public ThreeOrderStatus(int len, int arrange[])
    {
        super(len, arrange); 
    }

    public ArrayList<Integer> getNeighbors()
    {
        int num = this.num;
        ArrayList<Integer> neighbors = new ArrayList<Integer>();                     
        int index = -1;             
        int[] arrange = reCantor(num);
        for(int i = 0; i < this.len; i++)
        {
            if(arrange[i] == 0) 
            {
                index = i;
                break;
            }
        }
        
        if(index % 3 ==0)
        {
            if(indexValid(index - 3)) neighbors.add(exchange(arrange, index, index - 3)); 
            if(indexValid(index + 3)) neighbors.add(exchange(arrange, index, index + 3));
            neighbors.add(exchange(arrange, index, index + 1));
        } else if(index % 3 == 1) {
            if(indexValid(index - 3)) neighbors.add(exchange(arrange, index, index - 3)); 
            if(indexValid(index + 3)) neighbors.add(exchange(arrange, index, index + 3));
            neighbors.add(exchange(arrange, index, index + 1));
            neighbors.add(exchange(arrange, index, index - 1));
        } else {
            if(indexValid(index - 3)) neighbors.add(exchange(arrange, index, index - 3)); 
            if(indexValid(index + 3)) neighbors.add(exchange(arrange, index, index + 3));
            neighbors.add(exchange(arrange, index, index - 1));
        }
        return neighbors;
    }

    private boolean indexValid(int index)
    {
        if(index < 0 || index > 8) return false;
        return true;
    }
        
    private int exchange(int[] arrange, int index1, int index2)
    {
        int[] arrangeTmp = new int[9];     
        for(int i = 0; i < 9; i++)
        {
            arrangeTmp[i] = arrange[i];
        }

        arrangeTmp[index1] = arrange[index2];
        arrangeTmp[index2] = arrange[index1];
        return cantor(arrangeTmp);
    }
   
    //计算h
    public int calH()
    {
        int h = 0;
        int index1 = 0;
        int index2 = 0;
        for(int i = 1; i < 9; i++)  //计算 1 到 8 各个值距离最终状态的曼哈顿距离，0 不用计算
        {
            for(int k = 0; k < 9; k++){
                if(arrange[k] == i) index1 = k; 
                if(lastStatus[k] == i) index2 = k;
            }
            h += calManhDis(index1, index2);
        }

        return h;
    }

    private int calManhDis(int index1, int index2)
    {
        int y1 = index1 / 3;
        int x1 = index1 % 3;
        int y2 = index2 / 3;
        int x2 = index2 % 3;
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }

    public int search()
    {
        PriorityQueue<ThreeOrderStatus> openList = new PriorityQueue<ThreeOrderStatus>(1, new ThreeOrderStatusComparator());
        HashSet<Integer> openListAssist = new HashSet<Integer>();

        //LinkedList<ThreeOrderStatus> closeList = new LinkedList<ThreeOrderStatus>();
        HashSet<Integer> closeListAssist = new HashSet<Integer>();

        ThreeOrderStatus start = this;      
        start.g = 0;
        start.h = start.calH();
        start.f = start.g + start.h;
        openList.add(start);
        openListAssist.add(start.num);

        ThreeOrderStatus u, j; 
        ArrayList<Integer> neighbors = null;
        ThreeOrderStatus lastStatus = new ThreeOrderStatus(9, ThreeOrderStatus.lastStatus);
        while(openList.size() != 0)
        {
            u = openList.poll();         
            openListAssist.remove(u.num);

            //closeList.add(u);
            closeListAssist.add(u.num);

            if(u.equals(lastStatus)) return u.g;
                
            neighbors = u.getNeighbors();    
            for(Integer k : neighbors)   
            {
                j = new ThreeOrderStatus(len, k);
                j.h = j.calH();
                if(openListAssist.contains(j.num))         
                {
                    for(ThreeOrderStatus n : openList)
                    {
                        if(n.equals(j))
                        {
                            if(n.f > j.h + u.g + 1) 
                            {
                                openList.remove(n); 
                                j.g = u.g + 1;
                                openList.add(j);
                            }
                            break;
                        }
                    }
                } else if(closeListAssist.contains(j.num)) {
                    continue; 
                } else {
                    j.g = u.g + 1;
                    j.h = j.calH();
                    j.f = j.g + j.h;

                    openList.add(j);
                    openListAssist.add(j.num);
                }
            } //end for
        }  //end while
        return -1;
    }
}

class ThreeOrderStatusComparator implements Comparator<ThreeOrderStatus> 
{
    public int compare(ThreeOrderStatus x, ThreeOrderStatus y)
    {
        if(x.f > y.f) return 1;
        if(x.f < y.f) return -1;
        return 0;
    }
}
