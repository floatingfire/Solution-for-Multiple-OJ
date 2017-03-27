import java.io.*;
import java.util.*;

public class HDU_2457_DNA_repair {
	static class Node {
		Node g[], f;
		int id;
		boolean o;

		Node(int id) {
			g = new Node[KIND];
			f = null;
			this.id = id;
			o = false;
		}
	}

	static final int KIND = 4;
	static StreamTokenizer in;
	static PrintWriter out;
	static Node root, tree[];
	static int cnt, n;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static int trans(char c) {
		return c == 'A' ? 0 : c == 'C' ? 1 : c == 'G' ? 2 : 3;
	}

	static void insert(String s) {
		Node cur = root;
		for (int i = 0; i < s.length(); i++) {
			int c = trans(s.charAt(i));
			if (cur.g[c] == null) {
				cur.g[c] = new Node(cnt++);
			}
			cur = cur.g[c];
		}
		cur.o = true;
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		tree = new Node[cnt];
		tree[root.id] = root;
		for (int i = 0; i < KIND; i++) {
			if (root.g[i] != null) {
				q.add(root.g[i]);
				root.g[i].f = root;
			} else {
				root.g[i] = root;
			}
		}
		while (!q.isEmpty()) {
			Node cur = q.poll();
			tree[cur.id] = cur;
			for (int i = 0; i < KIND; i++) {
				if (cur.g[i] != null) {
					q.add(cur.g[i]);
					Node tmp = cur.f;
					while (tmp != root && tmp.g[i] == null) {
						tmp = tmp.f;
					}
					if (tmp.g[i] != null) {
						tmp = tmp.g[i];
					}
					cur.g[i].f = tmp;
					if (tmp.o) {
						cur.g[i].o = true;
					}
				} else {
					cur.g[i] = cur.f.g[i];
				}
			}
		}
	}

	static long dp(String t) {
		int f[][] = new int[t.length() + 1][cnt];
		for (int i = 0; i <= t.length(); i++) {
			for (int j = 0; j < cnt; j++) {
				f[i][j] = Integer.MAX_VALUE;
			}
		}
		f[0][0] = 0;
		for (int i = 0; i < t.length(); i++) {
			for (int j = 0; j < cnt; j++) {
				if (f[i][j] != Integer.MAX_VALUE) {
					for (int k = 0; k < KIND; k++) {
						if (!tree[j].g[k].o)
							f[i + 1][tree[j].g[k].id] = Math
									.min(f[i + 1][tree[j].g[k].id], f[i][j]
											+ (k != trans(t.charAt(i)) ? 1 : 0));
					}
				}
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < cnt; i++) {
			ans = Math.min(ans, f[t.length()][i]);
		}
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in, "ISO-8859-1"));
		out = new PrintWriter(System.out);
		for (int tst = 1;; tst++) {
			n = nextInt();
			if (n == 0) {
				break;
			}
			cnt = 0;
			root = new Node(cnt++);
			for (int i = 0; i < n; i++) {
				insert(next());
			}
			build();
			out.println("Case " + tst + ": " + dp(next()));
		}
		out.close();
	}
}