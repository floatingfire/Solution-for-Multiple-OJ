import java.io.*;

public class POJ_1743_Musical_Theme {
	static StreamTokenizer in;
	static PrintWriter out;
	static final int MAX = 88;
	static int n, s[], rank[], sa[], height[];

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static void da() {
		int m = MAX << 1;
		int cnt[] = new int[m + 1];
		int x[] = new int[n], y[] = new int[n];
		for (int i = 0; i < n; i++) {
			cnt[x[i] = s[i]]++;
		}
		for (int i = 1; i <= m; i++) {
			cnt[i] += cnt[i - 1];
		}
		for (int i = n - 1; i >= 0; i--) {
			sa[--cnt[x[i]]] = i;
		}
		for (int p = 1, i, tmp[], j = 1; p < n; j <<= 1, m = p) {
			for (p = 0, i = n - j; i < n; i++) {
				y[p++] = i;
			}
			for (i = 0; i < n; i++) {
				if (sa[i] >= j) {
					y[p++] = sa[i] - j;
				}
			}
			cnt = new int[m + 1];
			for (i = 0; i < n; i++) {
				cnt[x[y[i]]]++;
			}
			for (i = 1; i <= m; i++) {
				cnt[i] += cnt[i - 1];
			}
			for (i = n - 1; i >= 0; i--) {
				sa[--cnt[x[y[i]]]] = y[i];
			}
			for (tmp = x, x = y, y = tmp, p = 1, i = 1, x[sa[0]] = 0; i < n; i++) {
				x[sa[i]] = y[sa[i]] == y[sa[i - 1]]
						&& y[sa[i] + j] == y[sa[i - 1] + j] ? p - 1 : p++;
			}
		}
	}

	static void calheight() {
		for (int i = 0; i < n; i++) {
			rank[sa[i]] = i;
		}
		for (int i = 0, k = 0, j = 0; i < n; height[rank[i++]] = k) {
			for (k = k > 0 ? k - 1 : k, j = rank[i] == 0 ? n : sa[rank[i] - 1]; j != n
					&& s[i + k] == s[j + k]; k++)
				;
		}
	}

	static boolean check(int len) {
		int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
		for (int i = 1; i < n; i++) {
			if (len > height[i]) {
				if (max - min < len) {
					max = Integer.MIN_VALUE;
					min = Integer.MAX_VALUE;
				} else {
					return true;
				}
			}
			max = Math.max(max, sa[i]);
			min = Math.min(min, sa[i]);
		}
		return !(max - min < len);
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		while (true) {
			n = nextInt();
			if (n == 0) {
				break;
			}
			s = new int[n];
			for (int i = 0; i < n; i++) {
				s[i] = nextInt();
			}
			for (int i = 0; i < n - 1; i++) {
				s[i] = s[i] - s[i + 1] + MAX;
			}
			s[n - 1] = 0;
			sa = new int[n];
			rank = new int[n];
			height = new int[n];
			da();
			calheight();
			int l = 0, r = n, mid = (l + r) >> 1;
			while (r - l > 1) {
				if (check(mid)) {
					l = mid;
				} else {
					r = mid;
				}
				mid = (l + r) >> 1;
			}
			int ans = (check(r) ? r : l) + 1;
			out.println(ans < 5 ? 0 : ans);
		}
		out.close();
	}
}