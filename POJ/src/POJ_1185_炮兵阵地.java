import java.io.*;

public class POJ_1185_ÅÚ±øÕóµØ {
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

	static int cntone(int val) {
		int cnt = 0;
		while (val > 0) {
			cnt++;
			val -= -val & val;
		}
		return cnt;
	}

	static int dp(int map[], int n, int m) {
		m = 1 << m;
		int cnt = 0;
		int stu[] = new int[m];
		for (int i = 0; i < m; i++) {
			if ((i & (i << 1)) == 0 && (i & (i << 2)) == 0) {
				stu[i] = cntone(i);
				cnt++;
			}
		}
		int st[] = new int[cnt];
		int f[][][] = new int[2][cnt][cnt];
		for (int i = 0, p = 0; i < m; i++) {
			if ((i & (i << 1)) == 0 && (i & (i << 2)) == 0) {
				st[p++] = i;
				f[0][0][p - 1] = (i & map[0]) == 0 ? stu[i] : -1;
			}
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < cnt; j++) {
				if ((st[j] & map[i]) > 0) {
					continue;
				}
				for (int k = 0; k < cnt; k++) {
					if ((st[j] & st[k]) > 0) {
						continue;
					}
					f[i & 1][k][j] = -1;
					for (int l = 0; l < cnt; l++) {
						if ((st[j] & st[l]) > 0) {
							continue;
						}
						if (f[(i + 1) & 1][l][k] == -1) {
							continue;
						}
						f[i & 1][k][j] = Math.max(f[i & 1][k][j],
								f[(i + 1) & 1][l][k] + stu[st[j]]);
					}
				}
			}
		}
		int ans = 0;
		for (int in[] : f[(n + 1) & 1]) {
			for (int i : in) {
				ans = Math.max(ans, i);
			}
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int n = nextInt(), m = nextInt();
		int map[] = new int[n];
		for (int i = 0; i < n; i++) {
			String tmp = next();
			for (int j = m - 1; j >= 0; j--) {
				map[i] <<= 1;
				map[i] |= tmp.charAt(j) == 'H' ? 1 : 0;
			}
		}
		out.println(dp(map, n, m));
		out.close();
	}
}