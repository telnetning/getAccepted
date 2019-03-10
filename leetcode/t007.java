import static java.lang.Math.abs;

class Solution07 {
    public int reverse(int x) {
        boolean isNegetive = x < 0;

        if (isNegetive) {
            x = abs(x);
        }

        String xStr = String.valueOf(x);
        String xStrReverse = "";
        for(int i = xStr.length() - 1; i >= 0; i--) {
            xStrReverse += xStr.charAt(i);
        }

        int result = 0;
        try {
            result = Integer.parseInt(xStrReverse);
        } catch (NumberFormatException e) {
            return 0;
        }

        return isNegetive ? 0 - result : result;
    }
}

public class Leetcode_seven {
    public static void main(String[] args) {
        System.out.println((new Solution07()).reverse(123));
        System.out.println((new Solution07()).reverse(-123));
        System.out.println((new Solution07()).reverse(120));
    }
}
