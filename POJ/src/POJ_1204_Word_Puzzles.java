import java.io.*;
import java.util.*;

public class POJ_1204_Word_Puzzles {
	static class Node {
		Node next[], fail;
		int id;

		Node() {
			fail = null;
			next = new Node[128];
			id = -1;
		}
	}

	static class Data {
		int x, y, d;

		Data(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		public String toString() {
			return x + " " + y + " " + (char) ((d + 4) % 8 + 'A');
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;
	static Node root = new Node();
	static Data ans[];
	static int l, c, w, dirc[][] = { { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 } };
	static char t[][];

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static void insert(String p, int id) {
		Node cur = root;
		for (int i = p.length() - 1; i >= 0; i--) {
			char c = p.charAt(i);
			if (cur.next[c] == null) {
				cur.next[c] = new Node();
			}
			cur = cur.next[c];
		}
		cur.id = id;
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < 128; i++) {
			if (root.next[i] != null) {
				q.add(root.next[i]);
				root.next[i].fail = root;
			}
		}
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 128; i++) {
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
				}
			}
		}
	}

	static void dfs(int x, int y, int d) {
		Node cur = root;
		while (x > -1 && y > -1 && x < l && y < c) {
			while (cur != root && cur.next[t[x][y]] == null) {
				cur = cur.fail;
			}
			if (cur.next[t[x][y]] != null) {
				cur = cur.next[t[x][y]];
			}
			Node tmp = cur;
			while (tmp != root) {
				if (tmp.id != -1) {
					ans[tmp.id] = new Data(x, y, d);
					tmp.id = -1;
				}
				tmp = tmp.fail;
			}
			x -= dirc[d][0];
			y -= dirc[d][1];
		}
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		l = nextInt();
		c = nextInt();
		w = nextInt();
		t = new char[l][];
		ans = new Data[w];
		for (int i = 0; i < l; i++) {
			t[i] = next().toCharArray();
		}
		for (int i = 0; i < w; i++) {
			insert(next(), i);
		}
		build();
		for (int i = 0; i < l; i++) {
			dfs(i, 0, 1);
			dfs(i, 0, 2);
			dfs(i, 0, 3);
			dfs(i, c - 1, 5);
			dfs(i, c - 1, 6);
			dfs(i, c - 1, 7);
		}
		for (int i = 0; i < c; i++) {
			dfs(l - 1, i, 0);
			dfs(l - 1, i, 1);
			dfs(l - 1, i, 7);
			dfs(0, i, 3);
			dfs(0, i, 4);
			dfs(0, i, 5);
		}
		for (Data d : ans) {
			out.println(d);
		}
		out.close();
	}
}