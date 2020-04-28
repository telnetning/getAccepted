package moe.ncg.leetcode;

public class t304 {
    
    private int row;  //总行数
    private int col;  //总列数
    private int[][] sumM;
    
    //预先计算
    public t304(int[][] matrix) {
        row = matrix.length;
        if(row == 0) {
            col = 0;
        } else {
            col = matrix[0].length;
        }
        sumM = new int[row][col];
        
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(j == 0) {
                    sumM[i][j] = matrix[i][j];
                } else {
                    sumM[i][j] = matrix[i][j] + sumM[i][j - 1];
                }
            }
        }
        
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for(int i = row1; i <= row2; i++) {
            if(col1 == 0){
                res += sumM[i][col2];
            } else {
                res += sumM[i][col2] - sumM[i][col1 - 1];
            }
        }
        
        return res;
    }
}