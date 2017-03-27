import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class SPOJ_687_Repeats {
	static StreamTokenizer in;
	static final int MAX = 3;
	static final double log2 = Math.log(2);
	static int[] sa, ra, he, min[];

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static int sort(int l, int m, int n) {
		int i, cnt[] = new int[m];
		for (i = 0; i < n; i++) {
			cnt[ra[he[i]]]++;
		}
		for (i = 1; i < m; i++) {
			cnt[i] += cnt[i - 1];
		}
		for (i = n - 1; i >= 0; i--) {
			sa[--cnt[ra[he[i]]]] = he[i];
		}
		for (i = 1, m = 1, he[sa[0]] = 0; i < n; i++) {
			he[sa[i]] = ra[sa[i]] == ra[sa[i - 1]]
					&& ra[sa[i] + l] == ra[sa[i - 1] + l] ? m - 1 : m++;
		}
		int[] tmp = ra;
		ra = he;
		he = tmp;
		return m;
	}

	static void dacal(byte[] s) {
		int n = s.length;
		sa = new int[n];
		ra = new int[n];
		he = new int[n];
		for (int i = 0; i < n; i++) {
			ra[i] = s[i];
			he[i] = i;
		}
		for (int l = 1, m = sort(0, MAX, n); m < n; l <<= 1) {
			int p = 0;
			for (int i = n - l; i < n; i++) {
				he[p++] = i;
			}
			for (int i = 0; i < n; i++) {
				if (sa[i] >= l) {
					he[p++] = sa[i] - l;
				}
			}
			m = sort(l, m, n);
		}
		for (int i = 0, k = 0; i < n; i++) {
			k = k > 0 ? k - 1 : k;
			int j = ra[i] == 0 ? n : sa[ra[i] - 1];
			while (j < n && s[i + k] == s[j + k]) {
				k++;
			}
			he[ra[i]] = k;
		}
	}

	static void initrmq(int n) {
		min = new int[(int) (Math.log(n) / log2 + 1)][n];
		for (int i = 0; i < n; i++) {
			min[0][i] = he[i];
		}
		for (int i = 1; i < min.length; i++) {
			for (int j = 0; j + (1 << i - 1) < n; j++) {
				min[i][j] = Math.min(min[i - 1][j],
						min[i - 1][j + (1 << i - 1)]);
			}
		}
	}

	static int rmq(int l, int r) {
		l = ra[l];
		r = ra[r];
		if (l > r) {
			int t = l;
			l = r;
			r = t;
		}
		int k = (int) (Math.log(r - l) / log2);
		return Math.min(min[k][l + 1], min[k][r - (1 << k) + 1]);
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		for (int tst = nextInt(); tst > 0; tst--) {
			int n = nextInt();
			byte[] s = new byte[n + 1];
			for (int i = 0; i < n; i++) {
				s[i] = (byte) (next().charAt(0) - '`');
			}
			dacal(s);
			initrmq(n + 1);
			int max = 0;
			for (int l = 1; l <= n; l++) {
				for (int i = 0; i + l <= n; i += l) {
					int k = rmq(i, i + l);
					int t = i - (l - k % l);
					int ans = k / l + 1;
					if (t >= 0 && k % l > 0 && rmq(t, t + l) > k) {
						ans++;
					}
					max = Math.max(max, ans);
				}
			}
			System.out.println(max);
		}
	}
}