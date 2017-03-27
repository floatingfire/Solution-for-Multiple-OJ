import java.io.*;
import java.util.*;

public class HDU_4534_郑厂长系列故事_新闻净化 {
	static class Node {
		Node fail, next[];
		int id, val, mt;
		boolean ct;

		Node(int id) {
			this.id = id;
			next = new Node[26];
		}
	}

	static Node root, tree[];
	static int cnt, cmt;

	static void insert(String p, int val) {
		Node cur = root;
		for (char c : p.toCharArray()) {
			if (cur.next[c - 'a'] == null) {
				cur.next[c - 'a'] = new Node(cnt++);
			}
			cur = cur.next[c - 'a'];
		}
		if (val == 999) {
			cur.mt |= 1 << cmt++;
		} else if (val == -999) {
			cur.ct = true;
		} else {
			cur.val += val;
		}
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < 26; i++) {
			if (root.next[i] != null) {
				q.add(root.next[i]);
				root.next[i].fail = root;
			} else {
				root.next[i] = root;
			}
		}
		tree = new Node[cnt];
		tree[root.id] = root;
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			tree[tmp.id] = tmp;
			for (int i = 0; i < 26; i++) {
				if (tmp.next[i] != null) {
					q.add(tmp.next[i]);
					Node tsf = tmp.fail;
					while (tsf != root && tsf.next[i] == null) {
						tsf = tsf.fail;
					}
					if (tsf.next[i] != null) {
						tsf = tsf.next[i];
					}
					tmp.next[i].fail = tsf;
					tmp.next[i].ct |= tsf.ct;
					tmp.next[i].mt |= tsf.mt;
					tmp.next[i].val += tsf.val;
				} else {
					tmp.next[i] = tmp.fail.next[i];
				}
			}
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static void dp(String s) {
		final int stu = 1 << cmt, max = 286331153, min = -1886417009;
		int f[][][] = new int[2][cnt][stu];
		int g[][][] = new int[2][cnt][stu];
		for (int j = 0; j < f[0].length; j++) {
			for (int k = 0; k < f[0][j].length; k++) {
				f[0][j][k] = max;
				g[0][j][k] = min;
			}
		}
		f[0][0][0] = g[0][0][0] = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < cnt; j++) {
				for (int k = 0; k < stu; k++) {
					f[(i + 1) & 1][j][k] = max;
					g[(i + 1) & 1][j][k] = min;
				}
			}
			for (int j = 0; j < cnt; j++) {
				for (int k = 0; k < stu; k++) {
					if (f[i & 1][j][k] >= max) {
						continue;
					}
					if (f[(i + 1) & 1][j][k] > f[i & 1][j][k] + 1) {
						f[(i + 1) & 1][j][k] = f[i & 1][j][k] + 1;
						g[(i + 1) & 1][j][k] = g[i & 1][j][k];
					} else if (f[(i + 1) & 1][j][k] == f[i & 1][j][k] + 1
							&& g[(i + 1) & 1][j][k] < g[i & 1][j][k]) {
						g[(i + 1) & 1][j][k] = g[i & 1][j][k];
					}
					int id = s.charAt(i) - 'a';
					if (tree[j].next[id] == null)
						continue;
					int cur = tree[j].next[id].id;
					if (tree[cur].ct)
						continue;
					int val = tree[cur].val;
					int curk = k | tree[cur].mt;
					if (f[(i + 1) & 1][cur][curk] > f[i & 1][j][k]) {
						f[(i + 1) & 1][cur][curk] = f[i & 1][j][k];
						g[(i + 1) & 1][cur][curk] = g[i & 1][j][k] + val;
					} else if (f[(i + 1) & 1][cur][curk] == f[i & 1][j][k]
							&& g[(i + 1) & 1][cur][curk] < g[i & 1][j][k] + val) {
						g[(i + 1) & 1][cur][curk] = g[i & 1][j][k] + val;
					}
				}
			}
		}
		int a1 = max, a2 = min, n = s.length() & 1;
		for (int i = 0; i < cnt; i++) {
			if (f[n][i][stu - 1] < a1) {
				a1 = f[n][i][stu - 1];
				a2 = g[n][i][stu - 1];
			} else if (f[n][i][stu - 1] == a1 && g[n][i][stu - 1] > a2) {
				a2 = g[n][i][stu - 1];
			}
		}
		if (a1 >= max) {
			out.println("Banned");
		} else {
			out.println(a1 + " " + a2);
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int tst = 1, ttt = nextInt(); tst <= ttt; tst++) {
			cnt = cmt = 0;
			root = new Node(cnt++);
			for (int i = nextInt(); i > 0; i--) {
				insert(next(), nextInt());
			}
			build();
			out.print("Case " + tst + ": ");
			dp(next());
		}
		out.close();
	}
}