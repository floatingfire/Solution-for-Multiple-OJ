import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: msquare
 */
public class msquare {
	static class Pair {
		String s, o;

		Pair(String s, String o) {
			this.s = s;
			this.o = o;
		}
	}

	static BufferedReader in;
	static PrintWriter out;

	static int dic[][] = { { 7, 6, 5, 4, 3, 2, 1, 0 },
			{ 3, 0, 1, 2, 5, 6, 7, 4 }, { 0, 6, 1, 3, 4, 2, 5, 7 } };
	static int fac[] = { 5040, 720, 120, 24, 6, 2, 1, 1 };

	static int hash(String s) {
		char ans[] = s.toCharArray();
		int h = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = s.length() - 1; j >= i; j--) {
				if (ans[i] <= ans[j]) {
					ans[j]--;
				}
			}
			h += (ans[i] - '0') * fac[i];
		}
		return h;
	}

	static String change(String s, int o) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 8; i++) {
			sb.append(s.charAt(dic[o][i]));
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		// in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		in = new BufferedReader(new FileReader("msquare.in"));
		out = new PrintWriter(new FileWriter("msquare.out"));
		String end = in.readLine().replaceAll(" ", "");
		Queue<Pair> q = new LinkedList<Pair>();
		boolean vst[] = new boolean[40321];
		q.add(new Pair("12345678", ""));
		while (!q.isEmpty()) {
			Pair tmp = q.poll();
			if (tmp.s.equals(end)) {
				out.println(tmp.o.length());
				if (tmp.o.length() == 0) {
					out.println();
				}
				for (int i = 0; i < tmp.o.length(); i++) {
					if (i % 60 == 59 || i == tmp.o.length() - 1) {
						out.println(tmp.o.charAt(i));
					} else {
						out.print(tmp.o.charAt(i));
					}
				}
				break;
			}
			int h = hash(tmp.s);
			if (!vst[h]) {
				vst[h] = true;
				for (int i = 0; i < 3; i++) {
					q.add(new Pair(change(tmp.s, i), tmp.o + (char) (i + 'A')));
				}
			}
		}
		out.close();
	}
}
