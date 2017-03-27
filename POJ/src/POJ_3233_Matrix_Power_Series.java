import java.util.*;

public class POJ_3233_Matrix_Power_Series {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		long k = scan.nextLong();
		int m = scan.nextInt();
		long[][] a = new long[n][n];
		long[][] c = new long[n][n];
		for (int i = 0; i < n; i++) {
			c[i][i] = 1;
			for (int j = 0; j < n; j++) {
				a[i][j] = scan.nextInt();
			}
		}
		printOut(sum(a, k, c, m));
	}

	private static long[][] matrixMul(long[][] mat1, long[][] mat2, int m) {
		int n = mat1.length;
		long[][] matrix = new long[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					matrix[i][j] = (matrix[i][j] + mat1[i][k] * mat2[k][j]) % m;
				}
			}
		}
		return matrix;
	}

	private static long[][] matrixQuickMul(long[][] mat, long k, int m,
			long[][] c) {
		while (k > 0) {
			if ((k & 1) == 1)
				c = matrixMul(c, mat, m);
			mat = matrixMul(mat, mat, m);
			k >>= 1;
		}
		return c;
	}

	private static long[][] matrixSum(long[][] mat1, long[][] mat2, int m) {
		int n = mat1.length;
		long[][] c = new long[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				c[i][j] = (mat1[i][j] + mat2[i][j]) % m;
			}
		}
		return c;
	}

	private static long[][] sum(long[][] a, long k, long[][] c, int m) {
		if (k == 1)
			return a;
		else {
			if ((k & 1) == 0)
				return matrixMul(
						matrixSum(matrixQuickMul(a, k >> 1, m, c), c, m),
						sum(a, k >> 1, c, m), m);
			else
				return matrixSum(
						matrixMul(
								matrixSum(matrixQuickMul(a, k >> 1, m, c), c, m),
								sum(a, k >> 1, c, m), m),
						matrixQuickMul(a, k, m, c), m);
		}
	}

	private static void printOut(long[][] m) {
		int n = m.length;
		for (int i = 0; i < n; i++) {
			int j;
			for (j = 0; j < n - 1; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println(m[i][j]);
		}
	}
}
