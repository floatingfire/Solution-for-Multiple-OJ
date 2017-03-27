import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: prefix
 */
public class prefix {
	static BufferedReader in;
	static StringTokenizer st;
	static PrintWriter out;

	static class Node {
		Node fail, next[] = new Node[26];
		boolean out;
	}

	static Node root = new Node();
	static TreeSet<Integer> lens;
	static String tmp;

	static void insert(String p) {
		Node cur = root;
		for (char c : p.toCharArray()) {
			if (cur.next[c - 'A'] == null) {
				cur.next[c - 'A'] = new Node();
			}
			cur = cur.next[c - 'A'];
		}
		lens.add(p.length());
		cur.out = true;
	}

	static boolean search(String t) {
		Node cur = root;
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			if (cur.next[c - 'A'] == null) {
				return false;
			}
			cur = cur.next[c - 'A'];
		}
		return cur.out;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("prefix.in"));
		out = new PrintWriter(new FileWriter("prefix.out"));
		lens = new TreeSet<Integer>();
		while (true) {
			tmp = in.readLine();
			if (tmp.equals(".")) {
				break;
			}
			st = new StringTokenizer(tmp);
			while (st.hasMoreTokens()) {
				insert(st.nextToken());
			}
		}
		StringBuilder sb = new StringBuilder();
		while (true) {
			tmp = in.readLine();
			if (tmp == null) {
				break;
			}
			sb.append(tmp);
		}
		tmp = sb.toString();
		int f[] = new int[tmp.length() + 1];
		for (int i = tmp.length() - lens.first(); i >= 0; i--) {
			for (int j : lens) {
				if (i + j <= tmp.length() && search(tmp.substring(i, i + j))) {
					f[i] = Math.max(j + f[i + j], f[i]);
				}
			}
		}
		out.println(f[0]);
		out.close();
	}
}
