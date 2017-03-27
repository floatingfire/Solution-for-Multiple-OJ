import java.io.*;
import java.util.*;

/*
 ID: lloo1351
 LANG: JAVA
 PROG: contact
 */
public class contact {
	static BufferedReader in;
	static PrintWriter out;

	static class Node {
		Node f, g[] = new Node[2];
		boolean o;
		int cnt;
	}

	static Node root = new Node();
	static int a, b, n;
	static TreeSet<Pair> ans = new TreeSet<Pair>();

	static void init(int id, Node cur) {
		if (id < b) {
			cur.g[0] = new Node();
			cur.g[1] = new Node();
			if (id > a - 2) {
				cur.g[0].o = cur.g[1].o = true;
			}
			init(id + 1, cur.g[0]);
			init(id + 1, cur.g[1]);
		}
	}

	static void build() {
		Stack<Node> s = new Stack<Node>();
		for (int i = 0; i < 2; i++) {
			s.add(root.g[i]);
			root.g[i].f = root;
		}
		while (!s.isEmpty()) {
			Node cur = s.pop();
			for (int i = 0; i < 2; i++) {
				if (cur.g[i] != null) {
					s.add(cur.g[i]);
					Node tmp = cur.f;
					while (tmp != root && tmp.g[i] == null) {
						tmp = tmp.f;
					}
					if (tmp.g[i] != null) {
						tmp = tmp.g[i];
					}
					cur.g[i].f = tmp;
				}
			}
		}
	}

	static final int MAX = 200001;
	static byte t[] = new byte[MAX];

	static void read() throws Exception {
		for (int i = 0; i <= MAX; i++) {
			t[i] = (byte) (in.read() - '0');
			if (t[i] < 0) {
				t[i] = (byte) (in.read() - '0');
				if (t[i] < 0) {
					t[i] = (byte) (in.read() - '0');
					if (t[i] < 0) {
						break;
					}
				}
			}
		}
	}

	static void match() {
		Node cur = root;
		for (int i = 0; t[i] >= 0; i++) {
			while (cur != root && cur.g[t[i]] == null) {
				cur = cur.f;
			}
			if (cur.g[t[i]] != null) {
				cur = cur.g[t[i]];
			}
			Node tmp = cur;
			while (tmp != root) {
				if (tmp.o) {
					tmp.cnt++;
				}
				tmp = tmp.f;
			}
		}
	}

	static class Pair implements Comparable<Pair> {
		int cnt;
		String s;

		Pair(int cnt, String s) {
			this.cnt = cnt;
			this.s = s;
		}

		public int compareTo(Pair p) {
			return cnt != p.cnt ? p.cnt - cnt : s.length() != p.s.length() ? s
					.length() - p.s.length() : s.compareTo(p.s);
		}
	}

	static void dfs(int id, String s, Node cur) {
		if (id <= b) {
			if (cur.o && cur.cnt != 0) {
				ans.add(new Pair(cur.cnt, s));
			}
			dfs(id + 1, s + "0", cur.g[0]);
			dfs(id + 1, s + "1", cur.g[1]);
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new FileReader("contact.in"));
		out = new PrintWriter(new FileWriter("contact.out"));
		// in = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(in.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		read();
		init(0, root);
		build();
		match();
		dfs(0, "", root);
		Iterator<Pair> it = ans.iterator();
		Pair pre = it.next();
		int cnt = 0, bit = 0;
		out.println(pre.cnt);
		out.print(pre.s);
		while (it.hasNext()) {
			Pair p = it.next();
			if (p.cnt == pre.cnt) {
				if (bit + p.s.length() > 80) {
					out.println();
					out.print(p.s);
					bit = p.s.length();
				} else {
					out.print(" " + p.s);
					bit += p.s.length() + 1;
				}
			} else {
				if (++cnt == n) {
					break;
				}
				out.println();
				out.println(p.cnt);
				out.print(p.s);
				bit = p.s.length();
			}
			pre = p;
		}
		out.println();
		out.close();
	}
}
