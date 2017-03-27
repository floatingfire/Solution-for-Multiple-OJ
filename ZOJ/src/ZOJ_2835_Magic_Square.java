import java.io.*;

public class ZOJ_2835_Magic_Square {
	static StreamTokenizer in;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		bk: while (true) {
			int[] x = new int[1001];
			int n = nextInt();
			boolean b = true;
			if (n == 0) {
				break;
			} else if (n == 1) {
				nextInt();
				System.out.println("Yes");
				continue bk;
			} else if (n == 2) {
				b = false;
			}
			int[][] ms = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					ms[i][j] = nextInt();
					x[ms[i][j]] += 1;
					if (x[ms[i][j]] > 1) {
						b = false;
					}
				}
			}
			if (!b) {
				System.out.println("No");
				continue bk;
			}
			int sum = 0;
			for (int i = 0; i < n; i++) {
				int suml = 0;
				for (int j = 0; j < n; j++) {
					suml += ms[i][j];
				}
				sum = sum == 0 ? suml : sum;
				if (sum != suml) {
					System.out.println("No");
					continue bk;
				}
				suml = 0;
				for (int j = 0; j < n; j++) {
					suml += ms[j][i];
				}
				if (sum != suml) {
					System.out.println("No");
					continue bk;
				}
			}
			int[] sumx = new int[2];
			for (int i = 0; i < n; i++) {
				sumx[0] += ms[i][i];
				sumx[1] += ms[i][n - i - 1];
			}
			if (sum == sumx[0] && sum == sumx[1]) {
				System.out.println("Yes");
			} else {
				System.out.println("No");
			}
		}
	}
}
