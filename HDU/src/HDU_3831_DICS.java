import java.io.*;

public class HDU_3831_DICS {
	static BufferedReader in;
	static PrintWriter out;

	static int trans(char ch) {
		return ch < 'a' ? ch - 'A' + 26 : ch - 'a';
	}

	static int dp(String s, String t) {
		int MAX = 1000000000;
		int f[][][] = new int[s.length() + 1][t.length() + 1][53];
		for (int i = 0; i <= s.length(); i++) {
			for (int j = 0; j <= t.length(); j++) {
				for (int k = 0; k < 53; k++) {
					f[i][j][k] = MAX;
				}
			}
		}
		for (int i = 0; i <= s.length(); i++) {
			f[i][0][52] = i;
		}
		for (int i = 0; i <= t.length(); i++) {
			f[0][i][52] = i;
		}
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= t.length(); j++) {
				for (int k = 0; k < 52; k++) {
					if (k == trans(t.charAt(j - 1))) {
						f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - 1][k]);
					} else {
						f[i][j][k] = Math.min(Math.min(f[i][j][k],
								f[i - 1][j][k] + 1), Math.min(
								f[i][j - 1][k] + 1, f[i - 1][j - 1][k] + 1));
						f[i][j][trans(t.charAt(j - 1))] = Math.min(
								f[i][j][trans(t.charAt(j - 1))],
								f[i - 1][j - 1][k] + 1);
					}
				}
				if (s.charAt(i - 1) == t.charAt(j - 1)) {
					f[i][j][52] = Math.min(f[i][j][52], f[i - 1][j - 1][52]);
				} else {
					f[i][j][52] = Math.min(Math.min(f[i][j][52],
							f[i - 1][j][52] + 1), Math.min(f[i][j - 1][52] + 1,
							f[i - 1][j - 1][52] + 1));
					f[i][j][trans(t.charAt(j - 1))] = Math.min(
							f[i][j][trans(t.charAt(j - 1))],
							f[i - 1][j - 1][52] + 1);
				}
			}
		}
		int ans = MAX;
		for (int i : f[s.length()][t.length()]) {
			ans = Math.min(ans, i);
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		while (true) {
			String s = in.readLine();
			if (s.equals("#")) {
				break;
			}
			out.println(dp(s, in.readLine()));
		}
		out.close();
	}
}