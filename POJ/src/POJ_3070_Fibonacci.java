import java.util.*;

public class POJ_3070_Fibonacci {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		final int[][] A = { { 1, 1 }, { 1, 0 } };
		while (true) {
			long n = scan.nextLong();
			if (n == -1)
				break;
			int[][] fbi = matrixQuickMul(A, n);
			System.out.println(fbi[1][0]);
		}
	}

	private static int[][] matrixMul(int[][] a, int[][] b) {
		int[][] pro = new int[a.length][b[0].length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < a[0].length; k++) {
					pro[i][j] = (pro[i][j] + a[i][k] * b[k][j]) % 10000;
				}
			}
		}
		return pro;
	}

	private static int[][] matrixQuickMul(int[][] a, long b) {
		int[][] c = { { 1, 0 }, { 0, 1 } };
		while (b > 0) {
			if ((b & 1) == 1)
				c = matrixMul(c, a);
			a = matrixMul(a, a);
			b >>= 1;
		}
		return c;
	}
}
