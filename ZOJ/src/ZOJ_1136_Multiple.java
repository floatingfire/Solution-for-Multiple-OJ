import java.util.*;
import java.io.*;

public class ZOJ_1136_Multiple {
	class Node {
		int r;
		String m;

		Node(int remnum, String mult) {
			r = remnum;
			m = mult;
		}
	}

	StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	int x[], n, m;
	boolean dic[] = new boolean[5000];

	int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	String bfs() {
		Arrays.fill(dic, true);
		Arrays.sort(x);
		Queue<Node> que = new LinkedList<Node>();
		for (int i : x) {
			if (i != 0) {
				int t = i % n;
				if (t == 0) {
					return i + "";
				} else {
					que.add(new Node(t, i + ""));
					dic[t] = false;
				}
			}
		}
		while (!que.isEmpty()) {
			Node temp = que.poll();
			for (int i : x) {
				int t = (temp.r * 10 + i) % n;
				if (dic[t]) {
					if (t == 0) {
						return temp.m + i;
					} else {
						que.add(new Node(t, temp.m + i));
						dic[t] = false;
					}
				}
			}
		}
		return 0 + "";
	}

	void run() throws Exception {
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			n = (int) in.nval;
			m = nextInt();
			x = new int[m];
			for (int i = 0; i < m; i++) {
				x[i] = nextInt();
			}
			if (n == 0) {
				System.out.println("0");
			} else {
				System.out.println(bfs());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new ZOJ_1136_Multiple().run();
	}
}
