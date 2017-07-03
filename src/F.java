import java.util.Scanner;  
/** 
 * ��쳲���������<br/> 
 * <pre> 
 * [F(n+1) F(n)]    [1  1 ]^n   (n�η�,����ʹ�ù��ɷ�֤��)<br/> 
 * |           |   =|     |                     <br/> 
 * [F(n) F(n-1)]    [1  0 ]                     <br/> 
 * </pre> 
 * @author bing 
 * 
 */  
public class F {  
    // ��������  
    private static final int[][] UNIT = { { 1, 1 }, { 1, 0 } };  
    // ȫ0����  
    private static final int[][] ZERO = { { 0, 0 }, { 0, 0 } };  
  
    public static void main(String[] args) {  
        Scanner scanner = new Scanner(System.in);  
        while (true) {  
            // ��n��쳲�������,��0��ʼ  
            int n = scanner.nextInt();  
            long t1 = System.currentTimeMillis();  
            int[][] m = fb(n);  
            long t2 = System.currentTimeMillis();  
            System.out.println(m[0][1]);  //����ע������[0][0]��[0][1],���������˵���UNIT������ȡ[0][1]��Ϊ���յ�f(n)
            System.out.println("Time is: " + (t2 - t1));  
        }
    }  
      
    /** 
     * ��쳲��������� 
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
        // n��ż��
        if ((n & 1) == 0) {  
            int[][] matrix = fb(n >> 1);  
            return matrixMultiply(matrix, matrix);  
        }  
        // n������  
        int[][] matrix = fb((n - 1) >> 1);  
        return matrixMultiply(matrixMultiply(matrix, matrix), UNIT);  
    }  
      
    /** 
     * ������� 
     *  
     * @param m 
     *            r1*c1 
     * @param n 
     *            c1*c2 
     * @return �¾���,r1*c2 
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