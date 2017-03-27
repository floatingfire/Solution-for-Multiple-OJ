import java.io.*;

public class POJ_1141_Brackets_Sequence {
	static BufferedReader in;
	static PrintWriter out;

	static void dp(String s) {
		final int MAX = 1000000000;
		int f[][] = new int[s.length()][s.length()];
		int p[][] = new int[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			f[i][i] = 1;
		}
		for (int l = 1; l < s.length(); l++) {
			for (int i = 0; i + l < s.length(); i++) {
				if ((s.charAt(i) == '(' && s.charAt(i + l) == ')')
						|| (s.charAt(i) == '[' && s.charAt(i + l) == ']')) {
					f[i][i + l] = f[i + 1][i + l - 1];
					p[i][i + l] = -1;
				} else {
					f[i][i + l] = MAX;
				}
				for (int k = i; k < i + l; k++) {
					if (f[i][i + l] > f[i][k] + f[k + 1][i + l]) {
						f[i][i + l] = f[i][k] + f[k + 1][i + l];
						p[i][i + l] = k;
					}
				}
			}
		}
		print(p, s, 0, s.length() - 1);
	}

	static void print(int[][] p, String s, int l, int r) {
		if (l > r) {
			return;
		}
		if (l == r) {
			out.print(s.charAt(l) < '0' ? "()" : "[]");
			return;
		}
		if (p[l][r] == -1) {
			out.print(s.charAt(l));
			print(p, s, l + 1, r - 1);
			out.print(s.charAt(r));
			return;
		}
		print(p, s, l, p[l][r]);
		print(p, s, p[l][r] + 1, r);
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		dp(in.readLine());
		out.println();
		out.close();
	}

}