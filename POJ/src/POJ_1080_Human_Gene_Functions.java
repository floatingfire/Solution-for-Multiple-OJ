import java.io.*;

public class POJ_1080_Human_Gene_Functions {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static int trans(char ch) {
		switch (ch) {
		case 'A':
			return 0;
		case 'C':
			return 1;
		case 'G':
			return 2;
		case 'T':
			return 3;
		default:
			return 4;
		}
	}

	static int dic[][] = { { 5, -1, -2, -1, -3 }, { -1, 5, -3, -2, -4 },
			{ -2, -3, 5, -2, -2 }, { -1, -2, -2, 5, -1 }, { -3, -4, -2, -1, 0 } };

	static int dp(int n, String s, int m, String t) {
		int f[][] = new int[n + 1][m + 1];
		for (int i = 1; i <= m; i++) {
			f[0][i] = f[0][i - 1] + dic[trans(' ')][trans(t.charAt(i - 1))];
		}
		for (int i = 1; i <= n; i++) {
			f[i][0] = f[i - 1][0] + dic[trans(s.charAt(i - 1))][trans(' ')];
			for (int j = 1; j <= m; j++) {
				f[i][j] = Math
						.max(f[i - 1][j - 1]
								+ dic[trans(s.charAt(i - 1))][trans(t
										.charAt(j - 1))],
								Math.max(
										f[i][j - 1]
												+ dic[trans(' ')][trans(t
														.charAt(j - 1))],
										f[i - 1][j]
												+ dic[trans(s.charAt(i - 1))][trans(' ')]));
			}
		}
		return f[n][m];
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int tst = nextInt(); tst > 0; tst--) {
			int n = nextInt();
			String s = next();
			int m = nextInt();
			String t = next();
			out.println(dp(n, s, m, t));
		}
		out.close();
	}
}