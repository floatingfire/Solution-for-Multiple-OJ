import java.io.*;

public class POJ_1692_Crossed_Matchings {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int dp(int a[], int b[]) {
		int f[][] = new int[a.length][b.length];
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < b.length; j++) {
				f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
				if (a[i] != b[j]) {
					int k1, k2;
					for (k1 = i - 1; k1 > 0; k1--) {
						if (a[k1] == b[j]) {
							break;
						}
					}
					for (k2 = j - 1; k2 > 0; k2--) {
						if (a[i] == b[k2]) {
							break;
						}
					}
					if (k1 != 0 && k2 != 0) {
						f[i][j] = Math.max(f[i][j], f[k1 - 1][k2 - 1] + 2);
					}
				}
			}
		}
		return f[a.length - 1][b.length - 1];
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int tst = nextInt(); tst > 0; tst--) {
			int n = nextInt() + 1, m = nextInt() + 1;
			int[] a = new int[n], b = new int[m];
			for (int i = 1; i < n; i++) {
				a[i] = nextInt();
			}
			for (int i = 1; i < m; i++) {
				b[i] = nextInt();
			}
			out.println(dp(a, b));
		}
		out.close();
	}

}