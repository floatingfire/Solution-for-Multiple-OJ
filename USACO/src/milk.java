/*
 ID: lloo1351
 LANG: JAVA
 PROG: milk
 */
import java.io.*;
import java.util.*;

public class milk {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("milk.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("milk.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Integer.parseInt(st.nextToken());
		if (n == 0) {
			pw.println(0);
			pw.close();
			System.exit(0);
		}
		int m = Integer.parseInt(st.nextToken());
		int[][] ff = new int[2][m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			ff[0][i] = Integer.parseInt(st.nextToken());
			ff[1][i] = Integer.parseInt(st.nextToken());
		}
		quickSort(ff, 0, m - 1);
		long sum = 0;
		int i = 0;
		while (n > ff[1][i]) {
			sum += ff[0][i] * ff[1][i];
			n -= ff[1][i];
			i++;
		}
		sum += ff[0][i] * n;
		pw.println(sum);
		br.close();
		pw.close();
	}

	private static void quickSort(int[][] a, int q, int r) {
		if (q < r) {
			int p = position(a, q, r);
			quickSort(a, q, p - 1);
			quickSort(a, p + 1, r);
		}
	}

	private static int position(int[][] a, int q, int r) {
		int x = a[0][r];
		int i = q;
		for (int j = q; j < r; j++) {
			if (a[0][j] < x) {
				int t = a[0][j];
				a[0][j] = a[0][i];
				a[0][i] = t;
				t = a[1][j];
				a[1][j] = a[1][i];
				a[1][i] = t;
				i++;
			}
		}
		int t = a[0][r];
		a[0][r] = a[0][i];
		a[0][i] = t;
		t = a[1][r];
		a[1][r] = a[1][i];
		a[1][i] = t;
		return i;
	}
}
