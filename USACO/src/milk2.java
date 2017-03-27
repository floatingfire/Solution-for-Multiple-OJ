/*
 ID: lloo1351
 LANG: JAVA
 PROG: milk2
 */
import java.io.*;
import java.util.StringTokenizer;

public class milk2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter pw = new PrintWriter(new FileWriter("milk2.out"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long[][] fbe = new long[2][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			fbe[0][i] = Long.parseLong(st.nextToken());
			fbe[1][i] = Long.parseLong(st.nextToken());
		}
		quicksort(fbe, 0, n - 1);		
		long tl = fbe[1][0],tr=fbe[0][0];
		long maxa = tl-tr;
		long maxb = 0;
		for (int i = 1; i < n; i++) {
			if (tl < fbe[0][i]) {
				if (maxb < fbe[0][i] - tl)
					maxb = fbe[0][i] - tl;
				if(maxa<tl-tr)
					maxa=tl-tr;
				tl = fbe[1][i];
				tr=fbe[0][i];
			} else if (tl < fbe[1][i])
				tl = fbe[1][i];
		}
		pw.println(maxa + " " + maxb);
		br.close();
		pw.close();
	}

	private static void quicksort(long[][] a, int p, int r) {
		if (p < r) {
			int q = partition(a, p, r);
			quicksort(a, p, q - 1);
			quicksort(a, q + 1, r);
		}
	}

	private static int partition(long[][] ls, int p, int r) {
		long x = ls[0][r];
		int i = p - 1;
		for (int j = p; j < r; j++) {
			if (ls[0][j] <= x) {
				i++;
				long temp = ls[0][i];
				ls[0][i] = ls[0][j];
				ls[0][j] = temp;
				temp = ls[1][i];
				ls[1][i] = ls[1][j];
				ls[1][j] = temp;
			}
		}
		long temp = ls[0][i + 1];
		ls[0][i + 1] = ls[0][r];
		ls[0][r] = temp;
		temp = ls[1][i + 1];
		ls[1][i + 1] = ls[1][r];
		ls[1][r] = temp;
		return i + 1;
	}
}
