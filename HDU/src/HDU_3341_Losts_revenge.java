import java.io.*;
import java.util.*;

public class HDU_3341_Losts_revenge {
	static class Node {
		Node next[], fail;
		int id, cnt;

		Node(int id) {
			next = new Node[KIND];
			fail = null;
			this.id = id;
			cnt = 0;
		}
	}

	static final int KIND = 4;
	static StreamTokenizer in;
	static PrintWriter out;
	static Node root, tree[];
	static int n, cnt, num[];

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

	static void insert(String p) {
		Node cur = root;
		for (int i = 0; i < p.length(); i++) {
			int c = trans(p.charAt(i));
			if (cur.next[c] == null) {
				cur.next[c] = new Node(cnt++);
			}
			cur = cur.next[c];
		}
		cur.cnt++;
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
		tree = new Node[cnt];
		tree[root.id] = root;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			tree[cur.id] = cur;
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
					cur.next[i].cnt += tmp.cnt;
				} else {
					cur.next[i] = cur.fail.next[i];
				}
			}
		}
	}

	static int dp(String t) {
		for (int i = 0; i < t.length(); i++) {
			num[trans(t.charAt(i))]++;
		}
		int ts = 0, ACTG[] = new int[KIND];
		int hash[] = new int[KIND];
		hash[3] = 1;
		for (int i = 2; i >= 0; i--) {
			hash[i] = hash[i + 1] * (num[i + 1] + 1);
		}
		for (int i = 0; i < KIND; i++) {
			ts += hash[i] * num[i];
		}
		int dp[][] = new int[ts + 1][cnt];
		for (int i = 0; i < ts; i++) {
			for (int j = 0; j < cnt; j++) {
				dp[i][j] = -1;
			}
		}
		int ans = dp[0][0] = 0;
		for (int i = 0; i <= ts; ++i) {
			ACTG[0] = i / hash[0];
			ACTG[1] = (i % hash[0]) / hash[1];
			ACTG[2] = (i % hash[1]) / hash[2];
			ACTG[3] = i % hash[2];
			if (ACTG[0] > num[0] || ACTG[1] > num[1] || ACTG[2] > num[2]
					|| ACTG[3] > num[3])
				continue;

			for (int j = 0; j < cnt; ++j)
				if (dp[i][j] != -1) {
					for (int k = 0; k < KIND; ++k) {
						Node tmp = tree[j].next[k];
						if (ACTG[k] < num[k]
								&& dp[i + hash[k]][tmp.id] < dp[i][j] + tmp.cnt)
							dp[i + hash[k]][tmp.id] = dp[i][j] + tmp.cnt;
					}
				}
		}
		for (int i : dp[ts]) {
			ans = Math.max(ans, i);
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		for (int tst = 1;; tst++) {
			n = nextInt();
			if (n == 0) {
				break;
			}
			cnt = 0;
			root = new Node(cnt++);
			num = new int[KIND];
			for (int i = 0; i < n; i++) {
				insert(next());
			}
			build();
			out.println("Case " + tst + ": " + dp(next()));
		}
		out.close();
	}
}