import java.io.*;
import java.util.*;

public class ZOJ_3228_Searching_the_String {
	static class Node {
		Node g[], f;
		int len, id;

		Node(int len) {
			g = new Node[KIND];
			f = null;
			id = -1;
			this.len = len;
		}
	}

	static final int KIND = 26;
	static BufferedReader in;
	static PrintWriter out;
	static Node root[];
	static int n, id[], pos[], ans[];

	static int insert(int tp, String s, int id) {
		Node cur = root[tp];
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			if (cur.g[c] == null) {
				cur.g[c] = new Node(cur.len + 1);
			}
			cur = cur.g[c];
		}
		if (cur.id == -1) {
			cur.id = id;
		}
		return cur.id;
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int r = 0; r < root.length; r++) {
			for (int i = 0; i < KIND; i++) {
				if (root[r].g[i] != null) {
					q.add(root[r].g[i]);
					root[r].g[i].f = root[r];
				}
			}
			while (!q.isEmpty()) {
				Node cur = q.poll();
				for (int i = 0; i < KIND; i++) {
					if (cur.g[i] != null) {
						q.add(cur.g[i]);
						Node tmp = cur.f;
						while (tmp != root[r] && tmp.g[i] == null) {
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
	}

	static void Search(String t) {
		for (int r = 0; r < root.length; r++) {
			Node cur = root[r];
			for (int i = 0; i < t.length(); i++) {
				int c = t.charAt(i) - 'a';
				while (cur != root[r] && cur.g[c] == null) {
					cur = cur.f;
				}
				if (cur.g[c] != null) {
					cur = cur.g[c];
				}
				Node tmp = cur;
				while (tmp != root[r]) {
					if (tmp.id >= 0) {
						if (r == 0) {
							ans[id[tmp.id]]++;
						} else {
							if (pos[id[tmp.id]] + tmp.len <= i) {
								ans[id[tmp.id]]++;
								pos[id[tmp.id]] = i;
							}
						}
					}
					tmp = tmp.f;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		// in = new BufferedReader(new FileReader("test"));
		// out = new PrintWriter(new FileWriter("outtest"));
		for (int tst = 0;;) {
			String t = in.readLine();
			if (t == null) {
				break;
			} else if (t.isEmpty()) {
				continue;
			}
			tst++;
			n = Integer.parseInt(in.readLine());
			ans = new int[n];
			pos = new int[n];
			id = new int[n];
			Arrays.fill(pos, -1);
			root = new Node[2];
			root[0] = new Node(0);
			root[1] = new Node(0);
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				id[i] = insert(Integer.parseInt(st.nextToken()),
						st.nextToken(), i);
			}
			build();
			Search(t);
			out.println("Case " + tst);
			for (int i = 0; i < n; i++) {
				out.println(ans[id[i]]);
			}
			out.println();
			out.flush();
		}
		out.close();
	}
}