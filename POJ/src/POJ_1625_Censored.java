import java.io.*;
import java.math.*;
import java.util.*;

public class POJ_1625_Censored {
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

	static BufferedReader in;
	static PrintWriter out;
	static Node root, tree[];
	static int KIND, cnt, m, p, map[] = new int[256];

	static void insert(String s) {
		Node cur = root;
		for (int i = 0; i < s.length(); i++) {
			int c = map[s.charAt(i)];
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

	static BigInteger dp() {
		BigInteger f[][] = new BigInteger[m + 1][cnt];
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j < cnt; j++) {
				f[i][j] = BigInteger.ZERO;
			}
		}
		f[0][0] = BigInteger.ONE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < cnt; j++) {
				for (int k = 0; k < KIND; k++) {
					if (!tree[j].g[k].o) {
						f[i + 1][tree[j].g[k].id] = f[i + 1][tree[j].g[k].id]
								.add(f[i][j]);
					}
				}
			}
		}
		BigInteger ans = BigInteger.ZERO;
		for (BigInteger bi : f[m]) {
			ans = ans.add(bi);
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
		out = new PrintWriter(System.out);
		StringTokenizer st = new StringTokenizer(in.readLine());
		KIND = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		String str = in.readLine();
		for (int i = 0; i < KIND; i++) {
			map[str.charAt(i)] = i;
		}
		cnt = 0;
		root = new Node(cnt++);
		for (int i = 0; i < p; i++) {
			insert(in.readLine());
		}
		build();
		out.println(dp());
		out.close();
	}
}