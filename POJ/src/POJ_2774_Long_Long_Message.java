import java.io.*;

public class POJ_2774_Long_Long_Message {
	static BufferedReader in;
	static final int MAX = 28;
	static int[] sa, ra, he, min[];

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

	static void dacal(String s) {
		int n = s.length();
		sa = new int[n];
		ra = new int[n];
		he = new int[n];
		for (int i = 0; i < n; i++) {
			ra[i] = s.charAt(i) - '_';
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
			while (j < n && s.charAt(i + k) == s.charAt(j + k)) {
				k++;
			}
			he[ra[i]] = k;
		}
	}

	static boolean check(int id, int mid) {
		return sa[id - 1] < mid && sa[id] > mid || sa[id - 1] > mid
				&& sa[id] < mid;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		int mid = s.length();
		s += "_" + in.readLine() + "`";
		dacal(s);
		int max = 0;
		for (int i = 1; i < s.length(); i++) {
			if (check(i, mid)) {
				max = Math.max(max, he[i]);
			}
		}
		System.out.println(max);
	}
}