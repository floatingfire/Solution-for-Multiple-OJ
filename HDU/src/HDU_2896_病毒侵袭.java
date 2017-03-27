import java.io.*;
import java.util.*;

public class HDU_2896_²¡¶¾ÇÖÏ® {
	static class Node {
		Node fail, next[] = new Node[95];
		int out;
	}

	static Node root;

	static void insert(String p, int id) {
		Node cur = root;
		for (char c : p.toCharArray()) {
			if (cur.next[c - 32] == null) {
				cur.next[c - 32] = new Node();
			}
			cur = cur.next[c - 32];
		}
		cur.out = id;
	}

	static void build() {
		Queue<Node> q = new LinkedList<Node>();
		for (int i = 0; i < 95; i++) {
			if (root.next[i] != null) {
				q.add(root.next[i]);
				root.next[i].fail = root;
			}
		}
		while (!q.isEmpty()) {
			Node tmp = q.poll();
			for (int i = 0; i < 95; i++) {
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

	static int search(String t, int id) {
		Node cur = root;
		TreeSet<Integer> cnt = new TreeSet<Integer>();
		for (char c : t.toCharArray()) {
			while (cur != root && cur.next[c - 32] == null) {
				cur = cur.fail;
			}
			if (cur.next[c - 32] != null) {
				cur = cur.next[c - 32];
			}
			Node tmp = cur;
			while (tmp != root) {
				if (tmp.out != 0) {
					cnt.add(tmp.out);
				}
				tmp = tmp.fail;
			}
		}
		if (!cnt.isEmpty()) {
			out.print("web " + id + ":");
			while (!cnt.isEmpty()) {
				out.print(" " + cnt.pollFirst());
			}
			out.println();
			return 1;
		}
		return 0;
	}

	static BufferedReader in;
	static PrintWriter out;

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		root = new Node();
		int n = Integer.parseInt(in.readLine());
		for (int i = 1; i <= n; i++) {
			insert(in.readLine(), i);
		}
		build();
		int m = Integer.parseInt(in.readLine());
		int cnt = 0;
		for (int i = 1; i <= m; i++) {
			cnt += search(in.readLine(), i);
		}
		out.println("total: " + cnt);
		out.close();
	}
}