import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class POJ3468 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static SNode[] st;
	static int ps[];

	static void build(int root, int l, int r) throws Exception {
		st[root] = new SNode(l, r);
		if (l == r) {
			st[root].max = ps[l];
		} else {
			int mid = (l + r) >> 1;
			build(root << 1, l, mid);
			build(root << 1 | 1, mid + 1, r);
			upDate(root);
		}
	}

	static long query(int node, int l, int r) {
		pushDown(node);
		if (l <= st[node].left && r >= st[node].right) {
			return st[node].max;
		}
		int mid = (st[node].left + st[node].right) >> 1;
		long sum = 0;
		if (l <= mid) {
			sum += query(node << 1, l, r);
		}
		if (r > mid) {
			sum += query(node << 1 | 1, l, r);
		}
		return sum;
	}

	static void change(int node, int l, int r, int num) {
		pushDown(node);
		if (l <= st[node].left && r >= st[node].right) {
			st[node].lazy += num;
			pushDown(node);
			return;
		}
		int mid = (st[node].left + st[node].right) >> 1;
		if (l <= mid) {
			change(node << 1, l, r, num);
		}
		if (r > mid) {
			change(node << 1 | 1, l, r, num);
		}
		upDate(node);
	}

	static void upDate(int node) {
		if (st[node].left == st[node].right) {
			return;
		}
		pushDown(node);
		pushDown(node << 1);
		pushDown(node << 1 | 1);
		st[node].max = st[node << 1].max + st[node << 1 | 1].max;
	}

	static void pushDown(int node) {
		if (st[node].lazy != 0) {
			st[node].max += st[node].lazy
					* (st[node].right - st[node].left + 1);
			if (st[node].left != st[node].right) {
				st[node << 1].lazy += st[node].lazy;
				st[node << 1 | 1].lazy += st[node].lazy;
			}
			st[node].lazy = 0;
		}
	}

	static int nextInt() throws Exception {
		in.nextToken();
		return (int) in.nval;
	}

	static char nextChar() throws Exception {
		in.nextToken();
		return in.sval.charAt(0);
	}

	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(System.out);
		int n = nextInt();
		int m = nextInt();
		st = new SNode[3 * n];
		ps = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			ps[i] = nextInt();
		}
		build(1, 1, n);
		for (int i = 0; i < m; i++) {
			char re = nextChar();
			if (re == 'Q') {
				int l = nextInt();
				int r = nextInt();
				out.println(query(1, l, r));
			} else if (re == 'C') {
				int l = nextInt();
				int r = nextInt();
				int num = nextInt();
				change(1, l, r, num);
			}
		}
		out.close();
	}
}

class SNode {
	int left, right;
	long max, lazy;

	SNode(int l, int r) {
		left = l;
		right = r;
	}
}