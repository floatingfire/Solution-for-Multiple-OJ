import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class POJ2777 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static Ad[] st;

	static void build(int root, int l, int r) throws Exception {
		st[root] = new Ad(l, r);
		if (l == r) {
			st[root].color = 1 << 1;
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
			return st[node].color;
		}
		int mid = (st[node].left + st[node].right) >> 1;
		long sum = 0;
		if (l <= mid) {
			sum |= query(node << 1, l, r);
		}
		if (r > mid) {
			sum |= query(node << 1 | 1, l, r);
		}
		return sum;
	}

	static void change(int node, int l, int r, int num) {
		pushDown(node);
		if (l <= st[node].left && r >= st[node].right) {
			st[node].color = 1 << num;
			st[node].lazy = true;
			pushDown(node);
		} else {
			int mid = (st[node].left + st[node].right) >> 1;
			if (l <= mid) {
				change(node << 1, l, r, num);
			}
			if (r > mid) {
				change(node << 1 | 1, l, r, num);
			}
			upDate(node);
		}
	}

	static void upDate(int node) {
		if (st[node].left != st[node].right) {
			pushDown(node << 1);
			pushDown(node << 1 | 1);
			st[node].color = st[node << 1].color | st[node << 1 | 1].color;
		}
	}

	static void pushDown(int node) {
		if (st[node].lazy) {
			if (st[node].left != st[node].right) {
				st[node << 1].lazy = true;
				st[node << 1].color = st[node].color;
				st[node << 1 | 1].lazy = true;
				st[node << 1 | 1].color = st[node].color;
			}
			st[node].lazy = false;
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
		int l = nextInt();
		nextInt();
		int o = nextInt();
		st = new Ad[5 * l];
		build(1, 1, l);
		for (int i = 0; i < o; i++) {
			char re = nextChar();
			int a = nextInt();
			int b = nextInt();
			if (a > b) {
				int temp = a;
				a = b;
				b = temp;
			}
			if (re == 'P') {
				int count = 0;
				for (long j = query(1, a, b); j > 0; j >>= 1) {
					if ((j & 1) == 1) {
						count++;
					}
				}
				out.println(count);
			} else if (re == 'C') {
				int c = nextInt();
				change(1, a, b, c);
			}
		}
		out.close();
	}
}

class Ad {
	int left, right;
	long color;
	boolean lazy;

	Ad(int l, int r) {
		left = l;
		right = r;
	}
}