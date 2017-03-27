import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HDU_3068_最长回文 {
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

	static int getlength(String s) {
		int[] rds = new int[s.length() + 1 << 1];
		int id = 1, lf = 1, ans = 0;
		for (int i = 1; i < rds.length; i++) {
			if (i < id + lf) {
				rds[i] = Math.min(rds[(id << 1) - i], id + lf - i);
			}
			for (int j = i + rds[i]; j < rds.length
					&& check((i << 1) - j, j, s); j++) {
				rds[i]++;
			}
			if (id + lf < i + rds[i]) {
				id = i;
				lf = rds[i];
			}
			ans = Math.max(ans, rds[i] - 1);
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s = in.readLine();
			if (s == null) {
				break;
			} else if (s.isEmpty()) {
				continue;
			}
			System.out.println(getlength(s));
		}
	}
}