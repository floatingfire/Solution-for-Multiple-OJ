import java.io.*;

public class SPOJ_694_Distinct_Substrings {
	static BufferedReader in;
	static final int MAX = 128;
	static int[] sa, ra, he;

	static int sort(int l, int m, int n) {
		int cnt[] = new int[m];
		for (int i = 0; i < n; i++) {
			cnt[ra[i]]++;
		}
		for (int i = 1; i < m; i++) {
			cnt[i] += cnt[i - 1];
		}
		for (int i = n - 1; i >= 0; i--) {
			sa[--cnt[ra[he[i]]]] = he[i];
		}
		int[] tmp = ra;
		ra = he;
		he = tmp;
		ra[sa[0]] = m = 0;
		for (int i = 1; i < n; i++) {
			ra[sa[i]] = he[sa[i]] == he[sa[i - 1]]
					&& he[sa[i] + l] == he[sa[i - 1] + l] ? m : ++m;
		}
		return m + 1;
	}

	static void da(String s) {
		int n = s.length();
		sa = new int[n];
		ra = new int[n];
		he = new int[n];
		for (int i = 0; i < n; i++) {
			ra[i] = s.charAt(i);
			he[i] = i;
		}
		for (int l = 1, m = sort(0, MAX, n); m < n; m = sort(l, m, n), l <<= 1) {
			int p = 0;
			for (int i = n - l; i < n; i++) {
				he[p++] = i;
			}
			for (int i = 0; i < n; i++) {
				if (sa[i] >= l) {
					he[p++] = sa[i] - l;
				}
			}
		}
		for (int i = 0, k = 0; i < n; i++) {
			k = k == 0 ? k : k - 1;
			int j = ra[i] == 0 ? n : sa[ra[i] - 1];
			while (j < n && s.charAt(i + k) == s.charAt(j + k)) {
				k++;
			}
			he[ra[i]] = k;
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		for (int tst = Integer.parseInt(in.readLine()); tst > 0; tst--) {
			da(in.readLine() + (char) 0);
			int ans = 0;
			for (int i = 1; i < sa.length; i++) {
				ans += sa.length - 1 - sa[i] - he[i];
			}
			System.out.println(ans);
		}
	}
}
