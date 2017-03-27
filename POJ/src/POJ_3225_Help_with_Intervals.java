import java.io.*;
import java.util.*;

public class POJ_3225_Help_with_Intervals {
	static class Node {
		int l, r, cvr;
		boolean ngt;

		Node(int l, int r) {
			this.l = l;
			this.r = r;
			cvr = -1;
		}

		public String toString() {
			return l + " " + r + " " + cvr + " " + ngt;
		}
	}

	static final int MAX = 200000;
	static Node tree[] = new Node[MAX * 4];
	static int rem[] = new int[MAX];
	static int cnt;

	static void build(int id, int l, int r) {
		tree[id] = new Node(l, r);
		if (l != r) {
			int mid = (l + r) >> 1;
			build(id << 1, l, mid);
			build(id << 1 | 1, mid + 1, r);
		}
	}

	static void quary(int id) {
		if (tree[id].cvr == 1) {
			remark(id);
		} else if (tree[id].cvr == 0) {
			pushdown(id);
			quary(id << 1);
			quary(id << 1 | 1);
		}
	}

	static void update(int id, int l, int r, char op) {
		if (l <= tree[id].l && tree[id].r <= r) {
			if (op == 'U') {
				tree[id].cvr = 1;
				tree[id].ngt = false;
			} else if (op == 'D') {
				tree[id].cvr = -1;
				tree[id].ngt = false;
			} else if (op == 'C' || op == 'S') {
				if (tree[id].cvr != 0) {
					tree[id].cvr = -tree[id].cvr;
				} else {
					tree[id].ngt = !tree[id].ngt;
				}
			}
		} else {
			pushdown(id);
			int mid = (tree[id].l + tree[id].r) >> 1;
			if (l <= mid) {
				update(id << 1, l, r, op);
			} else if (op == 'I' || op == 'C') {
				tree[id << 1].cvr = -1;
				tree[id << 1].ngt = false;
			}
			if (mid < r) {
				update(id << 1 | 1, l, r, op);
			} else if (op == 'I' || op == 'C') {
				tree[id << 1 | 1].cvr = -1;
				tree[id << 1 | 1].ngt = false;
			}
		}
	}

	static void pushdown(int id) {
		if (tree[id].cvr != 0) {
			tree[id << 1].cvr = tree[id << 1 | 1].cvr = tree[id].cvr;
			tree[id << 1].ngt = tree[id << 1 | 1].ngt = false;
			tree[id].cvr = 0;
		}
		if (tree[id].ngt) {
			if (tree[id << 1].cvr != 0) {
				tree[id << 1].cvr = -tree[id << 1].cvr;
			} else {
				tree[id << 1].ngt = !tree[id << 1].ngt;
			}
			if (tree[id << 1 | 1].cvr != 0) {
				tree[id << 1 | 1].cvr = -tree[id << 1 | 1].cvr;
			} else {
				tree[id << 1 | 1].ngt = !tree[id << 1 | 1].ngt;
			}
			tree[id].ngt = false;
		}
	}

	static void remark(int id) {
		if (cnt == 0) {
			rem[cnt++] = tree[id].l;
			rem[cnt++] = tree[id].r;
		} else {
			if (rem[cnt - 1] + 1 == tree[id].l) {
				rem[cnt - 1] = tree[id].r;
			} else {
				rem[cnt++] = tree[id].l;
				rem[cnt++] = tree[id].r;
			}
		}
	}

	static void output() {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		cnt = 0;
		quary(1);
		if (rem[0] == 0) {
			out.println("empty set");
		}
		for (int i = 0; rem[i] != 0; i += 2) {
			char l = '[', r = ']';
			if ((rem[i] & 1) == 0) {
				rem[i] = (rem[i] >> 1) - 1;
				l = '(';
			} else {
				rem[i] >>= 1;
			}
			if ((rem[i + 1] & 1) == 0) {
				r = ')';
			}
			rem[i + 1] >>= 1;
			out.print("" + l + rem[i] + ',' + rem[i + 1] + r);
			out.print(rem[i + 2] != 0 ? " " : "");
		}
		out.println();
		out.close();
	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		build(1, 1, MAX);
		while (in.hasNext()) {
			String str = in.nextLine();
			int l, r;
			StringTokenizer st = new StringTokenizer(str.substring(3), ",[]()");
			if (str.charAt(2) == '[') {
				l = (Integer.parseInt(st.nextToken()) << 1) + 1;
			} else {
				l = (Integer.parseInt(st.nextToken()) << 1) + 2;
			}
			if (str.charAt(str.length() - 1) == ']') {
				r = (Integer.parseInt(st.nextToken()) << 1) + 1;
			} else {
				r = (Integer.parseInt(st.nextToken()) << 1);
			}
			update(1, l, r, str.charAt(0));

		}
		output();
	}
}