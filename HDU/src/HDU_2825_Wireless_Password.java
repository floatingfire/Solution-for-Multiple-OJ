import java.io.*;
import java.util.*;

public class HDU_2825_Wireless_Password {
	static class Node {
		Node g[], f;
		int id, cnt;

		Node(int id) {
			g = new Node[KIND];
			f = null;
			this.id = id;
			cnt = 0;
		}
	}

	static final int KIND = 26, MOD = 20090717;
	static StreamTokenizer in;
	static PrintWriter out;
	static Node root, tree[];
	static int cnt, n, m, p;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static void insert(String s, int id) {
		Node cur = root;
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			if (cur.g[c] == null) {
				cur.g[c] = new Node(cnt++);
			}
			cur = cur.g[c];
		}
		cur.cnt |= 1 << id;
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
					cur.g[i].cnt |= tmp.cnt;
				} else {
					cur.g[i] = cur.f.g[i];
				}
			}
		}
	}

	static long dp() {
		long f[][][] = new long[n + 1][cnt][1 << m];
		f[0][0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < cnt; j++) {
				for (int k = 0; k < 1 << m; k++) {
					if (f[i][j][k] != 0) {
						for (int l = 0; l < KIND; l++) {
							f[i + 1][tree[j].g[l].id][tree[j].g[l].cnt | k] = (f[i][j][k] + f[i + 1][tree[j].g[l].id][tree[j].g[l].cnt
									| k])
									% MOD;
						}
					}
				}
			}
		}
		long ans = 0;
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < 1 << m; j++) {
				int k = 0, c = j;
				while (c > 0) {
					c -= -c & c;
					k++;
				}
				if (k >= p) {
					ans = (ans + f[n][i][j]) % MOD;
				}
			}
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in, "ISO-8859-1"));
		out = new PrintWriter(System.out);
		while (true) {
			n = nextInt();
			m = nextInt();
			p = nextInt();
			if (n == 0 && m == 0 && p == 0) {
				break;
			}
			cnt = 0;
			root = new Node(cnt++);
			for (int i = 0; i < m; i++) {
				insert(next(), i);
			}
			build();
			out.println(dp());
		}
		out.close();
	}
}