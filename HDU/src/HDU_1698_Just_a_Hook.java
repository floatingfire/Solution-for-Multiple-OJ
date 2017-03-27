import java.io.*;

public class HDU_1698_Just_a_Hook {
	/*
	 * 线段树
	 * 维护区间和
	 * 更新操作为成段替换
	 * 查找操作为整段查询
	 */
	public static void main(String[] args) throws Exception {
		FastInput in = new FastInput();
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		int t = in.nextInt();
		for (int c = 1; c <= t; c++) {
			int n = in.nextInt();
			SegmentTree smt = new SegmentTree(n);
			for (int m = in.nextInt(); m > 0; m--) {
				smt.update(1, in.nextInt(), in.nextInt(), in.nextInt());
			}
			out.println("Case " + c + ": The total value of the hook is "
					+ smt.total() + ".");
		}
		out.close();
	}

	static class FastInput {
		private StreamTokenizer in;

		public FastInput() {
			in = new StreamTokenizer(new BufferedReader(new InputStreamReader(
					System.in)));
		}

		public int nextInt() throws Exception {
			in.nextToken();
			return (int) in.nval;
		}

		public long nextLong() throws Exception {
			in.nextToken();
			return (long) in.nval;
		}

		public double nextDouble() throws Exception {
			in.nextToken();
			return in.nval;
		}

		public String next() throws Exception {
			in.nextToken();
			return in.sval;
		}
	}

	static class SegmentTree {// 维护区间和
		class Node {
			int l, r, sum, lazy;

			Node(int l, int r) {
				this.l = l;
				this.r = r;
			}
		}

		Node tree[];

		public SegmentTree(int n) {
			tree = new Node[n * 4];
			build(1, 1, n);
		}

		void build(int id, int left, int right) {
			tree[id] = new Node(left, right);
			if (left == right) {
				tree[id].sum = 1;
			} else {
				int mid = (left + right) >> 1;
				build(id << 1, left, mid);
				build(id << 1 | 1, mid + 1, right);
				pushup(id);
			}
		}

		int total() {
			return tree[1].sum;
		}

		int query(int id, int left, int right) {
			// 区间求和
			int ans = 0;
			if (left <= tree[id].l && tree[id].r <= right) {
				ans = tree[id].sum;
			} else {
				pushdown(id);
				int mid = (tree[id].l + tree[id].r) >> 1;
				if (left <= mid) {
					ans += query(id << 1, left, right);
				}
				if (mid < right) {
					ans += query(id << 1 | 1, left, right);
				}
			}
			return ans;
		}

		void update(int id, int left, int right, int key) {
			// 成段替换
			if (left <= tree[id].l && tree[id].r <= right) {
				tree[id].sum = (tree[id].r - tree[id].l + 1) * key;
				tree[id].lazy = key;
			} else {
				pushdown(id);
				int mid = (tree[id].l + tree[id].r) >> 1;
				if (left <= mid) {
					update(id << 1, left, right, key);
				}
				if (mid < right) {
					update(id << 1 | 1, left, right, key);
				}
				pushup(id);
			}
		}

		void pushup(int id) {
			tree[id].sum = tree[id << 1].sum + tree[id << 1 | 1].sum;
		}

		void pushdown(int id) {
			if (tree[id].lazy != 0) {
				tree[id << 1].sum = tree[id].lazy
						* (tree[id << 1].r - tree[id << 1].l + 1);
				tree[id << 1 | 1].sum = tree[id].lazy
						* (tree[id << 1 | 1].r - tree[id << 1 | 1].l + 1);
				tree[id << 1].lazy = tree[id << 1 | 1].lazy = tree[id].lazy;
				tree[id].lazy = 0;
			}
		}

	}

}
