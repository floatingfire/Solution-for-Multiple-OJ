import java.io.*;
import java.util.*;

public class HDU_2296_Ring {
	static class Node {
		Node next[], fail;
		int id, val;

		Node(int id) {
			next = new Node[26];
			fail = null;
			val = 0;
			this.id = id;
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;
	static Node root, tree[];
	static int cnt, n, m;

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static void insert(String p, int val) {
		Node cur = root;
		for (char c : p.toCharArray()) {
			if (cur.next[c - 'a'] == null) {
				cur.next[c - 'a'] = new Node(cnt++);
			}
			cur = cur.next[c - 'a'];
		}
		cur.val = val;
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
			Node cur = q.poll();
			tree[cur.id] = cur;
			for (int i = 0; i < 26; i++) {
				if (cur.next[i] != null) {
					q.add(cur.next[i]);
					Node tmp = cur.fail;
					while (tmp != root && tmp.next[i] == null) {
						tmp = tmp.fail;
					}
					if (tmp.next[i] != null) {
						tmp = tmp.next[i];
					}
					cur.next[i].fail = tmp;
					if (tmp.val != 0) {
						cur.next[i].val += tmp.val;
					}
				} else {
					cur.next[i] = cur.fail.next[i];
				}
			}
		}
	}

	static String dp() {
		int f[][] = new int[n + 1][cnt];
		String r[][] = new String[n + 1][cnt];
		int max = f[0][0] = 1;
		r[0][0] = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < cnt; j++) {
				if (f[i][j] != 0) {
					for (int k = 0; k < 26; k++) {
						Node cur = tree[j].next[k];
						if (f[i + 1][cur.id] < f[i][j] + cur.val) {
							f[i + 1][cur.id] = f[i][j] + cur.val;
							r[i + 1][cur.id] = r[i][j] + (char) (k + 'a');
						} else if (f[i + 1][cur.id] == f[i][j] + cur.val
								&& r[i + 1][cur.id].compareTo(r[i][j]
										+ (char) (k + 'a')) > 0) {
							r[i + 1][cur.id] = r[i][j] + (char) (k + 'a');
						}
					}
				}
			}
		}
		for (int in[] : f) {
			for (int i : in) {
				max = Math.max(max, i);
			}
		}
		if (max == 1) {
			return "";
		}
		String ans = " ";
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < cnt; j++) {
				if (f[i][j] == max
						&& (ans.equals(" ") || (ans.length() > r[i][j].length() || (ans
								.length() == r[i][j].length() && ans
								.compareTo(r[i][j]) > 0)))) {
					ans = r[i][j];
				}
			}
		}
		return ans;
	}

	public static void main(String args[]) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int t = nextInt(); t > 0; t--) {
			n = nextInt();
			m = nextInt();
			cnt = 0;
			root = new Node(cnt++);
			String p[] = new String[m];
			for (int i = 0; i < m; i++) {
				p[i] = next();
			}
			for (int i = 0; i < m; i++) {
				insert(p[i], nextInt());
			}
			build();
			out.println(dp());
		}
		out.close();
	}
}