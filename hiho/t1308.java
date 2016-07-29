import java.util.Scanner;
import java.util.LinkedList;
import java.io.FileInputStream;

public class t1308 {

    static int min[][][] = new int[3][9][9];  //存储bfs的结果
    static int direction[][] = {{-2, 1}, {-2, -1}, {2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1 ,-2}};

    public static void main(String[] args) throws Exception
    {
        /*
         * 用于重定向进行对比测试
        FileInputStream fis = new FileInputStream("./t1308.data");
        System.setIn(fis);
        */
        Scanner scanner = new Scanner(System.in);                
        int N = scanner.nextInt();
        //System.out.println(N);
        int[] point = new int[6];
        char[] tmp;
        int minTmp;
        
        int minX = 0;
        int minY = 0;
        for(int i = 0; i < N; i++)
        {
            //初始化 
            int ans = 100000; 
            initMin();
            //读入数据
            for(int j = 0; j < 3; j++)
            {
                tmp = scanner.next().toCharArray();
                point[j] = Integer.parseInt(String.valueOf(tmp[0] - 'A')) + 1;   
                point[j + 3] = Integer.parseInt(String.valueOf(tmp[1]));
            }
            
            solve(point);

            //计算到达每一个点的最短步骤
            for(int j = 1; j < 9; j++) 
            {
                for(int k = 1; k < 9; k++) 
                {
                    minTmp = min[0][j][k] + min[1][j][k] + min[2][j][k];
                    if(ans > minTmp)
                    {
                        minX = j; minY = k; 
                    }
                    ans = ans > minTmp ? minTmp : ans;    
                }
            }
            System.out.println(ans);
            //System.out.println(minX);
            //System.out.println(minY);
        }  //end for
    }

    private static int solve(int[] point)
    {
        int x,y;
        for(int j = 0; j < 3; j++)  //对于三个点分别计算,结果存储在min[][][]中
        {
            x = point[j];
            y = point[j + 3];
            
            bfs(j, x, y);    
        }

        
        return 0; 
    }
    
    private static void bfs(int j, int x, int y){
    
        class Point {
            public int x; 
            public int y;

            public Point(int x, int y)
            {
                this.x = x;
                this.y = y;
            }
        }
        
        Point tmpPoint;
        LinkedList<Point> queue = new LinkedList<Point>();                    
        
        min[j][x][y] = 0;

        int next_x, next_y;
        queue.add(new Point(x, y));

        while(queue.size() != 0)
        {
            //System.out.println(queue.size());
            tmpPoint = queue.removeFirst(); 
            //8个方向，[1, -2],[1, 2],[-1, -2], [-1, 2], [2, -1], [2, 1], [-2, 1], [-2, -1]
            for(int i = 0; i < 8; i++) 
            {
                next_x = tmpPoint.x + direction[i][0]; 
                next_y = tmpPoint.y + direction[i][1];

                if(isValid(next_x, next_y) && (min[j][next_x][next_y] == -1)) 
                {
                    queue.add(new Point(next_x, next_y));
                    min[j][next_x][next_y] = min[j][tmpPoint.x][tmpPoint.y] + 1;
                }
            }
        }  //end while
    }
    
    private static boolean isValid(int x, int y)
    {
        if(x < 1 || x > 8)  return false;
        if(y < 1 || y > 8)  return false;
        return true;
    }

    private static void initMin()
    {
        for(int i = 0; i < 3; i++) 
        {
            for(int j = 0; j < 9; j++) 
            {
                for(int k = 0; k < 9; k++)
                    min[i][j][k] = -1;
            }
        }
    }
}
