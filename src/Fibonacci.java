import java.util.Scanner;  
/** 
 * 求斐波那契数列<br/> 
 * <pre> 
 * [F(n+1) F(n)]    [1  1 ]^n   (n次方,可以使用归纳法证明)<br/> 
 * |           |   =|     |                     <br/> 
 * [F(n) F(n-1)]    [1  0 ]                     <br/> 
 * </pre> 
 * @author bing 
 * 
 */  
public class F {  
    // 关联矩阵  
    private static final int[][] UNIT = { { 1, 1 }, { 1, 0 } };  
    // 全0矩阵  
    private static final int[][] ZERO = { { 0, 0 }, { 0, 0 } };  
  
    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);  
        while (true) {  
            // 第n个斐波那契数,从0开始  
            int n = scanner.nextInt();  
            long t1 = System.currentTimeMillis();  
            int[][] m = fb(n);  
            long t2 = System.currentTimeMillis();  
            System.out.println(m[0][1]);  //这里注意区别[0][0]与[0][1],由于最后相乘的是UNIT矩阵，则取[0][1]做为最终的f(n)
            System.out.println("Time is: " + (t2 - t1));  
        }
    }  
      
    /** 
     * 求斐波那契数列 
     *  
     * @param n 
     * @return 
     */  
    public static int[][] fb(int n) {  
        if (n == 0) {  
            return ZERO;  
        }  
        if (n == 1) {  
            return UNIT;  
        }  
        // n是偶数
        if ((n & 1) == 0) {  
            int[][] matrix = fb(n >> 1);  
            return matrixMultiply(matrix, matrix);  
        }  
        // n是奇数  
        int[][] matrix = fb((n - 1) >> 1);  
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);  
    }  
      
    /** 
     * 矩阵相乘 
     *  
     * @param m 
     *            r1*c1 
     * @param n 
     *            c1*c2 
     * @return 新矩阵,r1*c2 
     */  
    public static int[][] matrixMultiply(int[][] m, int[][] n) {  
        int rows = m.length;  
        int cols = n[0].length;  
        int[][] r = new int[rows][cols];  
        for (int i = 0; i < rows; i++) {  
            for (int j = 0; j < cols; j++) {  
                r[i][j] = 0;  
                for (int k = 0; k < m[i].length; k++) {  
                    r[i][j] += m[i][k] * n[k][j];  
                }  
            }  
        }  
        return r;  
    } 
}
