package moe.ncg.leetcode;

//use breadth-first search to solve it
import java.util.ArrayDeque;
//import java.util.Arrays;

class Solution {

	class Point 
	{
		int dimon1;  //indicate column 
		int dimon2;	//indicate row	

		public Point(int dimon1, int dimon2)
		{
			this.dimon1 = dimon1;
			this.dimon2 = dimon2;
		}
	}

    public int numIslands(char[][] grid) 
    {
    	int dimon1Num = grid.length;
    	if(dimon1Num == 0) return 0;
    	int dimon2Num = grid[0].length;
    	//System.out.println("dimon1Num:" + dimon1Num + "  dimon2Num:" + dimon2Num);
    	ArrayDeque<Point> queue = new ArrayDeque<Point>();
    	//queue.add(Point(0, 0));  //add the first end to begin breadth-first search

    	int count = 0;	//the result to return 	
    	int curDimon1 = 0;  //current min row which point is not all visited
    	int[] dimon1Max = new int[dimon1Num];  //the last to visit for every row

    	while(dimon1Max[dimon1Num - 1] < dimon2Num)	
    	{
    		//System.out.println(curDimon1);	
    		if(grid[curDimon1][dimon1Max[curDimon1]] == '0' || grid[curDimon1][dimon1Max[curDimon1]] == '2')
    		{
    			grid[curDimon1][dimon1Max[curDimon1]] = '2';  //num 2 indicate this point is visited
    			if(dimon1Max[curDimon1] < dimon2Num - 1) 
    			{
    				dimon1Max[curDimon1]++;	
    			} else {
    				if(curDimon1 == dimon1Num - 1) 
    				{
    					return count;
    				} else {
    					curDimon1++;
    				}
				}

    		} else {
    			//a new island
    			count++;
    			queue.add(new Point(curDimon1, dimon1Max[curDimon1]));
    			grid[curDimon1][dimon1Max[curDimon1]] = '2';  //num 2 indicate this point is visited
	    		while(queue.peek() != null )	
	    		{
					Point cur = queue.poll();	    				

					if(cur.dimon1 - 1 >= 0) {
						if(grid[cur.dimon1 - 1][cur.dimon2] == '1'){
							queue.add(new Point(cur.dimon1 - 1, cur.dimon2));	
							grid[cur.dimon1 - 1][cur.dimon2] = '2';
						}
					}	

					if(cur.dimon1 + 1 < dimon1Num) {  //行数加1
						if(grid[cur.dimon1 + 1][cur.dimon2] == '1') {
							queue.add(new Point(cur.dimon1 + 1, cur.dimon2));
							grid[cur.dimon1 + 1][cur.dimon2] = '2';	
						}	
					}

					if(cur.dimon2 + 1 < dimon2Num) {  //列数加1
						if(grid[cur.dimon1][cur.dimon2 + 1] == '1') {
							queue.add(new Point(cur.dimon1, cur.dimon2 + 1));
							grid[cur.dimon1][cur.dimon2 + 1] = '2';	
						}	
					}

					if(cur.dimon2 - 1 >= 0) {
						if(grid[cur.dimon1][cur.dimon2 - 1] == '1') {
							queue.add(new Point(cur.dimon1, cur.dimon2 - 1));
							grid[cur.dimon1][cur.dimon2 - 1] = '2';	
						}	
					}
	    		}  //end while

	    		//System.out.println(Arrays.toString(grid[0]));
	    		//System.out.println(Arrays.toString(grid[1]));
	    		//System.out.println(Arrays.toString(grid[2]));
    		}  //end if-else  
    	}  //end while

    	return count;
    }

}

public class t200
{
	public static void main(String[] args){
		Solution s = new Solution();
/*		char[][] grid = new char[][]{
			{'1','1','0','0','0'}, 
			{'1','1','0','0','0'},
			{'0','0','1','0','0'},
			{'0','0','0','1','1'}		
		};*/
		char[][] grid = new char[][]{
			{'1', '1', '1'},
			{'0', '1', '1'},
			{'1', '1', '1'},
		};
		System.out.println(s.numIslands(grid));
	}
}

