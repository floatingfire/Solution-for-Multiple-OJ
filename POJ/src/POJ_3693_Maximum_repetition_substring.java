import java.io.*;
import java.util.*;

public class POJ_3693_Maximum_repetition_substring {
	static BufferedReader in;
	static final int MAX = 128;
	static int[] sa, ra, he;
	static int min[][];

	static void daAndCal(String s) {
		ra = new int[s.length()];
		he = new int[s.length()];
		sa = new int[s.length()];
		int cnt[] = new int[MAX];
		for (int i = 0; i < s.length(); i++) {
			cnt[ra[i] = s.charAt(i)]++;
		}
		for (int i = 1; i < cnt.length; i++) {
			cnt[i] += cnt[i - 1];
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			sa[--cnt[ra[i]]] = i;
		}
		for (int j = 1, p = MAX, i, tmp[]; j < s.length(); j <<= 1) {
			cnt = new int[p];
			for (p = 0, i = s.length() - j; i < s.length(); i++) {
				he[p++] = i;
			}
			for (i = 0; i < s.length(); i++) {
				if (sa[i] >= j) {
					he[p++] = sa[i] - j;
				}
			}
			for (i = 0; i < s.length(); i++) {
				cnt[ra[he[i]]]++;
			}
			for (i = 1; i < cnt.length; i++) {
				cnt[i] += cnt[i - 1];
			}
			for (i = s.length() - 1; i >= 0; i--) {
				sa[--cnt[ra[he[i]]]] = he[i];
			}
			for (tmp = ra, ra = he, he = tmp, i = 1, p = 1, ra[sa[0]] = 0; i < s
					.length(); i++) {
				ra[sa[i]] = he[sa[i]] == he[sa[i - 1]]
						&& he[sa[i] + j] == he[sa[i - 1] + j] ? p - 1 : p++;
			}
		}
		for (int i = 0, k = 0, j = 0; i < s.length(); he[ra[i++]] = k) {
			for (k = k > 0 ? k - 1 : k, j = ra[i] == 0 ? s.length()
					: sa[ra[i] - 1]; j != s.length()
					&& s.charAt(i + k) == s.charAt(j + k); k++)
				;
		}
	}

	static void initrmq(String p) {
		min = new int[p.length()][(int) (Math.log(p.length()) / Math.log(2) + 1)];
		for (int i = 0; i < p.length(); i++) {
			min[i][0] = he[i];
		}
		for (int i = 1; i < min[0].length; i++) {
			for (int j = 0; j + (1 << i - 1) < p.length(); j++) {
				min[j][i] = Math.min(min[j][i - 1],
						min[j + (1 << i - 1)][i - 1]);
			}
		}
	}

	static int rmq(int l, int r) {
		if (l > r) {
			int tmp = l;
			l = r;
			r = tmp;
		}
		int len = (int) (Math.log(r - l) / Math.log(2));
		return Math.min(min[l + 1][len], min[r - (1 << len) + 1][len]);
	}

	static void work(int tst, String p) {
		daAndCal(p);
		initrmq(p);
		int max = 0;
		Queue<Integer> len = new LinkedList<Integer>();
		for (int l = 1; l < p.length(); l++) {
			for (int i = 0; i + l < p.length(); i += l) {
				int k = rmq(ra[i], ra[i + l]);
				int ans = k / l + 1;
				int t = i - (l - k % l);
				if (t >= 0 && k % l != 0 && rmq(ra[t], ra[t + l]) > k) {
					ans++;
				} else {
					t += l;
				}
				if (ans > max) {
					max = ans;
					len.clear();
					len.add(l);
				} else if (ans == max && len.peek() != l) {
					len.add(l);
				}
			}
		}
		for (int i = 0; i < p.length(); i++) {
			for (int j : len) {
				if (sa[i] + j < p.length()
						&& rmq(ra[sa[i]], ra[sa[i] + j]) >= (max - 1) * j) {
					System.out.println("Case " + tst + ": "
							+ p.substring(sa[i], sa[i] + max * j));
					return;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		for (int tst = 1;; tst++) {
			String p = in.readLine() + "0";
			if (p.equals("#0")) {
				break;
			}
			work(tst, p);
		}
	}
}