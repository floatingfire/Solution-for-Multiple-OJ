import java.io.*;
import java.util.*;

public class POJ_2778_DNA_Sequence {
	static class Node {
		Node next[], fail;
		int id;
		boolean out;

		Node(int id) {
			next = new Node[4];
			fail = null;
			this.id = id;
		}
	}

	static StreamTokenizer in;
	static PrintWriter out;
	static Node root = new Node(0);
	static int cnt = 1, mod = 100000;

	static void insert(int p[]) {
		Node cur = root;
		for (int i : p) {
			if (cur.next[i] == null) {
				cur.next[i] = new Node(cnt++);
			}
			cur = cur.next[i];
		}
		cur.out = true;
	}

	static int[] trans(String s) {
		int ans[] = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			ans[i] = c == 'A' ? 0 : c == 'C' ? 1 : c == 'G' ? 2 : 3;
		}
		return ans;
	}

	static long[][] mul(long a[][], long b[][]) {
		long ans[][] = new long[a.length][b[0].length];
		for (int i = 0; i < ans.length; i++) {
			for (int j = 0; j < ans[0].length; j++) {
				for (int k = 0; k < b.length; k++) {
					ans[i][j] = (ans[i][j] + a[i][k] * b[k][j] % mod) % mod;
				}
			}
		}
		return ans;
	}

	static long pow(long a[][], int n) {
		long ans = 0;
		long b[][] = a;
		n--;
		while (n > 0) {
			if ((n & 1) == 1) {
				b = mul(b, a);
			}
			a = mul(a, a);
			n >>= 1;
		}
		for (long i : b[0]) {
			ans = (ans + i) % mod;
		}
		return ans;
	}

	static long[][] build() {
		long ans[][] = new long[cnt][cnt];
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < 4; i++) {
			if (root.next[i] != null) {
				q.add(root.next[i]);
				root.next[i].fail = root;
			} else {
				root.next[i] = root;
			}
		}
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 4; i++) {
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
					if (tmp.out) {
						cur.next[i].out = true;
					}
				} else {
					cur.next[i] = cur.fail.next[i];
				}
			}
		}
		q.add(root);
		boolean rem[] = new boolean[cnt];
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (!rem[cur.id]) {
				rem[cur.id] = true;
				for (int i = 0; i < 4; i++) {
					if (!cur.next[i].out) {
						q.add(cur.next[i]);
						ans[cur.id][cur.next[i].id]++;
					}
				}
			}
		}
		return ans;
	}

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static String next() throws Exception {
		in.nextToken();
		return in.sval;
	}

	public static void main(String[] args) throws Exception {
		in = new StreamTokenizer(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		int m = nextInt(), n = nextInt();
		for (int i = 0; i < m; i++) {
			insert(trans(next()));
		}
		out.println(pow(build(), n));
		out.close();
	}
}