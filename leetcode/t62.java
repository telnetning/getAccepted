public class t62 {
    public static void main(String[] args) {
        System.out.println(uniquePaths(1, 1));     
    }

    public static int uniquePaths(int m, int n) {
        return calPoint(1, 1, m, n); 
    }

    public static int calPoint(int x, int y, int m, int n) {
        if(x == m || y == n) return 1;
        return calPoint(x + 1, y, m, n) + calPoint(x, y + 1, m, n);
    }
}
