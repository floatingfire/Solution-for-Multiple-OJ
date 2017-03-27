import java.io.*;
import java.math.*;
import java.util.*;

public class HDU_2243_考研路茫茫_单词情节 {
	static class Node {
		Node g[], f;
		int id;
		boolean o;

		Node(int id) {
			g = new Node[26];
			f = null;
			this.id = id;
			o = false;
		}
	}

	static BufferedReader in;
	static Node root;
	static int cnt;
	static long one[][];

	static void insert(String s) {
		Node cur = root;
		for (int i = 0; i < s.length(); i++) {
			char c = (char) (s.charAt(i) - 'a');
			if (cur.g[c] == null) {
				cur.g[c] = new Node(cnt++);
			}
			cur = cur.g[c];
		}
		cur.o = true;
	}

	static long[][] build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < 26; i++) {
			if (root.g[i] != null) {
				q.add(root.g[i]);
				root.g[i].f = root;
			} else {
				root.g[i] = root;
			}
		}
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i = 0; i < 26; i++) {
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
		boolean rem[] = new boolean[cnt];
		long ans[][] = new long[cnt << 1][cnt << 1];
		for (int i = 0; i < ans.length; i++) {
			for (int j = cnt; j < ans[0].length; j++) {
				if (i == j || i + cnt == j) {
					ans[i][j] = 1;
				}
			}
		}
		q.add(root);
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (!rem[cur.id]) {
				rem[cur.id] = true;
				for (int i = 0; i < 26; i++) {
					if (!cur.g[i].o) {
						q.add(cur.g[i]);
						ans[cur.id][cur.g[i].id] += 1;
					}
				}
			}
		}
		return ans;
	}

	static long[][] mul(long a[][], long b[][]) {
		long ans[][] = new long[a.length][b.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < b.length; k++) {
					ans[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return ans;
	}

	static long pow(long a[][], int n) {
		long ans[][] = new long[a.length][a[0].length];
		for (int i = 0; i < a.length; i++) {
			ans[i][i] = 1;
		}
		while (n > 0) {
			if ((n & 1) == 1) {
				ans = mul(ans, a);
			}
			a = mul(a, a);
			n >>= 1;
		}
		long sum = 0;
		for (long l : ans[0]) {
			sum += l;
		}
		return sum;
	}

	static long sumpow(long a, int n) {
		long ans[][] = { { 1, 0 }, { 0, 1 } };
		long b[][] = { { a, 1 }, { 0, 1 } };
		while (n > 0) {
			if ((n & 1) == 1) {
				ans = mul(ans, b);
			}
			b = mul(b, b);
			n >>= 1;
		}
		return ans[0][0] + ans[0][1];
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = in.readLine();
			if (str == null) {
				break;
			}
			StringTokenizer st = new StringTokenizer(str);
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			cnt = 0;
			root = new Node(cnt++);
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				insert(st.nextToken());
			}
			one = new long[cnt << 1][cnt << 1];
			for (int i = 0; i < one.length; i++) {
				one[i][i] = 1;
			}
			long ans = sumpow(26, l) - pow(build(), l);
			System.out.println(ans >= 0 ? ans : BigInteger.ONE.shiftLeft(64)
					.add(BigInteger.valueOf(ans)));
		}
	}
}