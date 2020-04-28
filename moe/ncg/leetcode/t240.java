/*
 */

//方法1

public class t240 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        if(m == 0 && n == 0) return false;
        
        for(int i = 0; i < n; i++) {
            if(matrix[i][0] > target) break;
            if(matrix[i][0] == target) return true;
            for(int j = 0; j < m; j++) {
                if(matrix[i][j] < target) continue;
                if(matrix[i][j] == target) return true;
                break;
            }
        }
        
        return false;
    }
}

//方法2 O(m+n)
class Solution02 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        if(m == 0 && n == 0) return false;
        
        int i = 0;
        int j = m - 1;
        while(i < n && j >= 0) {
            if(matrix[i][j] == target) return true;
            if(matrix[i][j] > target) {
                j -= 1;
                continue;
            }
            if(matrix[i][j] < target) i += 1;
        }
        
        return false;
    }
}