class Solution 
{
	static int[] resCache = new int[100];
	static {
		resCache[0] = 1;
		resCache[1] = 1;
	}
    public int numTrees(int n) 
    {
    	if(resCache[n] != 0) return resCache[n];

    	int count = 0;
		if(n <= 1) return 1;        

		for(int i = 0; i < n; i++)
		{
			count += numTrees(i) * numTrees(n - 1 - i);	
		}

		resCache[n] = count;
		return count;
    }
}

public class t096
{
	public static void main(String[] args)
	{
		Solution s = new Solution();
		System.out.println(s.numTrees(3));	
	}
}
