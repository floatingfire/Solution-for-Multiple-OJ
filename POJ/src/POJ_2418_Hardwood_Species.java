import java.io.*;

public class POJ_2418_Hardwood_Species {
	static class Node {
		Node next[] = new Node[128];
		double cnt;
	}

	static BufferedReader in;
	static PrintWriter out;
	static Node root = new Node();
	static double cnt = 0;

	static void insert(String p) {
		Node cur = root;
		for (char c : p.toCharArray()) {
			if (cur.next[c] == null) {
				cur.next[c] = new Node();
			}
			cur = cur.next[c];
		}
		cur.cnt++;
		cnt++;
	}

	static void dfs(Node cur, String ans) {
		if (cur.cnt != 0) {
			out.printf("%s %.4f", ans, cur.cnt / cnt * 100);
			out.println();
		} else {
			for (int i = 0; i < 128; i++) {
				if (cur.next[i] != null) {
					dfs(cur.next[i], ans + (char) i);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(System.out);
		try {
			while (true) {
				String tmp = in.readLine();
				if (tmp.isEmpty()) {
					break;
				}
				insert(tmp);
			}
			dfs(root, new String());
			out.close();
		} catch (Exception e) {
			dfs(root, new String());
			out.close();
		}
	}
}