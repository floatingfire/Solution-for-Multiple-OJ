import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: lamps
 */
public class lamps {
	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static int n, c, tmp, on, off;
	static TreeSet<String> ans;
	static final int sta = 63;

	static boolean check(int ope) {
		return ((sta ^ ope) & on) == on && ((sta ^ ope) & off) == 0;
	}

	static String trans(int val) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < n; j++) {
			sb.append((val >> j % 6) & 1);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new FileReader("lamps.in"));
		out = new PrintWriter(new FileWriter("lamps.out"));
		// in = new StreamTokenizer(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));
		n = nextInt();
		c = nextInt();
		on = 0;
		off = 0;
		ans = new TreeSet<String>();
		while (true) {
			tmp = nextInt();
			if (tmp-- == -1) {
				break;
			}
			on |= 1 << tmp % 6;
		}
		while (true) {
			tmp = nextInt();
			if (tmp-- == -1) {
				break;
			}
			off |= 1 << tmp % 6;
		}
		int dic[] = { 63, 21, 42, 9 };
		if (c == 0) {
			if (check(0)) {
				ans.add(trans(sta));
			}
		} else if (c == 1) {
			for (int i = 0; i < 4; i++) {
				if (check(dic[i])) {
					ans.add(trans(sta ^ dic[i]));
				}
			}
		} else if (c == 2) {
			if (check(0)) {
				ans.add(trans(sta));
			}
			for (int i = 0; i < 3; i++) {
				if (check(dic[i])) {
					ans.add(trans(sta ^ dic[i]));
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (check(dic[i])) {
					ans.add(trans(sta ^ dic[i]));
				}
				if (check(dic[i] ^ dic[3])) {
					ans.add(trans(sta ^ dic[i] ^ dic[3]));
				}
			}
		}
		if (ans.isEmpty()) {
			out.println("IMPOSSIBLE");
		}
		for (String s : ans) {

			out.println(s);
		}
		out.close();
	}
}