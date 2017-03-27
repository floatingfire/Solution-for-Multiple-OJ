import java.io.*;

public class POJ_3974_Palindrome {
	static BufferedReader in;

	static char trans(int id, String s) {
		if (id <= 0) {
			return '$';
		} else if ((id & 1) == 1) {
			return '#';
		} else {
			return s.charAt(id - 1 >> 1);
		}
	}

	static boolean check(int lid, int rid, String s) {
		return trans(lid, s) == trans(rid, s);
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		for (int tst = 1;; tst++) {
			String s = in.readLine();
			if (s.equals("END")) {
				break;
			}
			int id = 1, lf = 1, ans = 0;
			int p[] = new int[s.length() + 1 << 1];
			for (int i = 1; i < p.length; i++) {
				if (i < id + lf) {
					p[i] = Math.min(p[(id << 1) - i], id + lf - i);
				}
				for (int j = i + p[i]; j < p.length
						&& check((i << 1) - j, j, s); j++) {
					p[i]++;
				}
				if (lf + id < p[i] + i) {
					lf = p[i];
					id = i;
				}
				ans = Math.max(ans, p[i] - 1);
			}
			System.out.println("Case " + tst + ": " + ans);
		}
	}
}