import java.io.*;
import java.util.*;

public class ZOJ_3430_Detect_the_Virus {
	static final int KIND = 256;

	static class Node {
		Node next[], fail;
		int cnt;
		boolean vst;

		Node() {
			next = new Node[KIND];
			fail = null;
			cnt = 0;
			vst = false;
		}
	}

	static BufferedReader in;
	static PrintWriter out;
	static Node root;
	static int map[];

	static void init() {
		map = new int[128];
		for (int i = 'A'; i <= 'Z'; i++) {
			map[i] = i - 'A';
		}
		for (int i = 'a'; i <= 'z'; i++) {
			map[i] = i - 'a' + 26;
		}
		for (int i = '0'; i <= '9'; i++) {
			map[i] = i - '0' + 52;
		}
		map['+'] = 62;
		map['/'] = 63;
	}

	static int[] trans(String s) {
		s = s.replaceAll("=", "");
		int ans[] = new int[s.length() * 3 / 4];
		int tail = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			int a = map[s.charAt(i)];
			int b = map[s.charAt(i + 1)];
			if ((i & 3) == 0) {
				ans[tail++] = (a << 2) + (b >> 4);
			} else if ((i & 3) == 1) {
				ans[tail++] = ((a & 15) << 4) + (b >> 2);
			} else if ((i & 3) == 2) {
				ans[tail++] = ((a & 3) << 6) + b;
			}
		}
		return ans;
	}

	static void insert(int p[]) {
		Node cur = root;
		for (int i : p) {
			if (cur.next[i] == null) {
				cur.next[i] = new Node();
			}
			cur = cur.next[i];
		}
		cur.cnt++;
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < KIND; i++) {
			if (root.next[i] != null) {
				q.add(root.next[i]);
				root.next[i].fail = root;
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
				}
			}
		}
	}

	static int search(int t[]) {
		int cnt = 0;
		Node cur = root;
		Stack<Node> chg = new Stack<Node>();
		for (int i : t) {
			while (cur != root && cur.next[i] == null) {
				cur = cur.fail;
			}
			if (cur.next[i] != null) {
				cur = cur.next[i];
			}
			Node tmp = cur;
			while (tmp != root && !tmp.vst) {
				cnt += tmp.cnt;
				tmp.vst = true;
				chg.add(tmp);
				tmp = tmp.fail;
			}
		}
		while (!chg.isEmpty()) {
			chg.pop().vst = false;
		}
		return cnt;
	}

	public static void main(String args[]) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		init();
		while (true) {
			String s = in.readLine();
			if (s == null) {
				break;
			}
			if (s.isEmpty()) {
				continue;
			}
			root = new Node();
			for (int i = Integer.parseInt(s); i > 0; i--) {
				insert(trans(in.readLine()));
			}
			build();
			for (int i = Integer.parseInt(in.readLine()); i > 0; i--) {
				out.println(search(trans(in.readLine())));
			}
			out.println();
		}
		out.close();
	}
}