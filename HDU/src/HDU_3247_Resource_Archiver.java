import java.io.*;
import java.util.*;

public class HDU_3247_Resource_Archiver {
	static class Node {
		Node next[], fail;
		int cnt, id;
		boolean tp;

		Node(int i) {
			next = new Node[KIND];
			fail = null;
			id = i;
			tp = false;
			cnt = 0;
		}
	}

	static final int KIND = 2, MAX = 1000000000;
	static BufferedReader in;
	static PrintWriter out;
	static Node root, end[];
	static int n, m, cnt, map[][];

	static int trans(char c) {
		return c - '0';
	}

	static Node insert(String p, int id) {
		Node cur = root;
		for (int i = 0; i < p.length(); i++) {
			int c = trans(p.charAt(i));
			if (cur.next[c] == null) {
				cur.next[c] = new Node(cnt++);
			}
			cur = cur.next[c];
		}
		if (id != -1) {
			cur.cnt |= 1 << id;
		} else {
			cur.tp = true;
		}
		return cur;
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < KIND; i++) {
			if (root.next[i] != null) {
				q.add(root.next[i]);
				root.next[i].fail = root;
			} else {
				root.next[i] = root;
			}
		}
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < KIND; i++) {
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
					cur.next[i].cnt |= tmp.cnt;
					cur.next[i].tp |= tmp.tp;
				} else {
					cur.next[i] = cur.fail.next[i];
				}
			}
		}
	}

	static void dfs(Node r, int id) {
		Queue<Node> q = new LinkedList<Node>();
		q.add(r);
		int dis[] = new int[cnt];
		Arrays.fill(dis, MAX);
		dis[r.id] = 0;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < KIND; i++) {
				if (!cur.next[i].tp && dis[cur.next[i].id] == MAX) {
					dis[cur.next[i].id] = dis[cur.id] + 1;
					q.add(cur.next[i]);
				}
			}
		}
		for (int i = 0; i <= n; i++) {
			map[id][i] = dis[end[i].id];
		}
	}

	static int dp() {
		int f[][] = new int[1 << n][n + 1];
		for (int i = 0; i < 1 << n; i++) {
			for (int j = 0; j <= n; j++) {
				f[i][j] = MAX;
			}
		}
		f[0][0] = 0;
		for (int i = 0; i < 1 << n; i++) {
			for (int j = 0; j <= n; j++) {
				if (f[i][j] != MAX) {
					for (int k = 0; k <= n; k++) {
						f[i | end[k].cnt][k] = Math.min(f[i | end[k].cnt][k],
								f[i][j] + map[j][k]);
					}
				}
			}
		}
		int ans = MAX;
		for (int i : f[f.length - 1]) {
			ans = Math.min(ans, i);
		}
		return ans == MAX ? -1 : ans;
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		while (true) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if (n == 0) {
				break;
			}
			cnt = 0;
			root = new Node(cnt++);
			end = new Node[n + 1];
			end[0] = root;
			for (int i = 1; i <= n; i++) {
				end[i] = insert(in.readLine(), i - 1);
			}
			for (int i = 0; i < m; i++) {
				insert(in.readLine(), -1);
			}
			build();
			map = new int[n + 1][n + 1];
			for (int i = 0; i <= n; i++) {
				dfs(end[i], i);
			}
			out.println(dp());
		}
		out.close();
	}
}