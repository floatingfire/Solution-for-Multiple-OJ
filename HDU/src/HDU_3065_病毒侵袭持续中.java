import java.io.*;
import java.util.*;

public class HDU_3065_²¡¶¾ÇÖÏ®³ÖÐøÖÐ {
	static class Node {
		Node fail, next[];
		int out;

		Node() {
			fail = null;
			next = new Node[26];
			out = -1;
		}
	}

	static Node root;

	static void insert(String p, int id) {
		Node cur = root;
		for (char c : p.toCharArray()) {
			if (cur.next[c - 65] == null) {
				cur.next[c - 65] = new Node();
			}
			cur = cur.next[c - 65];
		}
		cur.out = id;
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < 26; i++) {
			if (root.next[i] != null) {
				q.add(root.next[i]);
				root.next[i].fail = root;
			}
		}
		while (!q.isEmpty()) {
			Node tmp = q.poll();
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
				}
			}
		}
	}

	static int[] search(String t, int n) {
		Node cur = root;
		int cnt[] = new int[n];
		for (char c : t.toCharArray()) {
			if (c < 65 || c > 90) {
				cur = root;
				continue;
			}
			while (cur != root && cur.next[c - 65] == null) {
				cur = cur.fail;
			}
			if (cur.next[c - 65] != null) {
				cur = cur.next[c - 65];
			}
			Node tmp = cur;
			while (tmp != root) {
				if (tmp.out != -1) {
					cnt[tmp.out]++;
				}
				tmp = tmp.fail;
			}
		}
		return cnt;
	}

	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws Exception {
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					System.out)));
			while (true) {
				root = new Node();
				int n = Integer.parseInt(in.readLine());
				String dic[] = new String[n];
				for (int i = 0; i < n; i++) {
					dic[i] = in.readLine();
					insert(dic[i], i);
				}
				build();
				int cnt[] = search(in.readLine(), n);
				for (int i = 0; i < n; i++) {
					if (cnt[i] != 0) {
						out.println(dic[i] + ": " + cnt[i]);
					}
				}
				out.flush();
			}
		} catch (Exception e) {
			out.close();
			System.exit(0);
		}
	}
}