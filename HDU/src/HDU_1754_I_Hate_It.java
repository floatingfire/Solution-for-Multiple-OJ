import java.io.*;
public class HDU_1754_I_Hate_It {
	/*
	 * 线段树
	 * 维护区间最大值
	 * 更新操作为单点替换
	 * 查询操作为成段查询
	 */
	public static void main(String[] args) throws Exception {
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(System.out)));
		FastInput in = new FastInput();
		while (in.in.nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in.in.nval;
			int m = in.nextInt();
			int val[] = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				val[i] = in.nextInt();
			}
			SegmentTree smt = new SegmentTree(val, 1, n);
			for (int i = 0; i < m; i++) {
				char c = in.next().charAt(0);
				if (c == 'Q') {
					out.println(smt.quary(1, in.nextInt(), in.nextInt()));
				} else {
					smt.update(1, in.nextInt(), in.nextInt());
				}
			}
		}
		out.close();
	}

	static class FastInput {
		StreamTokenizer in;

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

	static class SegmentTree {
		class Node {
			int l, r, max, lazy;

			Node(int l, int r) {
				this.l = l;
				this.r = r;
			}
		}

		Node tree[];
		int value[];

		public SegmentTree(int val[], int left, int right) {
			value = val;
			tree = new Node[(right - left) * 3];
			build(1, left, right);
		}

		void build(int id, int left, int right) {
			tree[id] = new Node(left, right);
			if (left == right) {
				tree[id].max = value[left];
			} else {
				int mid = (left + right) >> 1;
				build(id << 1, left, mid);
				build(id << 1 | 1, mid + 1, right);
				pushup(id);
			}
		}

		int quary(int id, int left, int right) {
			int ans = Integer.MIN_VALUE;
			if (left <= tree[id].l && tree[id].r <= right) {
				ans = tree[id].max;
			} else {
				pushdown(id);
				int mid = (tree[id].l + tree[id].r) >> 1;
				if (left <= mid) {
					ans = max(ans, quary(id << 1, left, right));
				}
				if (mid < right) {
					ans = max(ans, quary(id << 1 | 1, left, right));
				}
			}
			return ans;
		}

		void update(int id, int pos, int key) {
			if (pos <= tree[id].l && tree[id].r <= pos) {
				tree[id].max = key;
				tree[id].lazy = key;
			} else {
				pushdown(id);
				int mid = (tree[id].l + tree[id].r) >> 1;
				if (pos <= mid) {
					update(id << 1, pos, key);
				} else {
					update(id << 1 | 1, pos, key);
				}
				pushup(id);
			}
		}

		void pushup(int id) {
			if (tree[id].l != tree[id].r) {
				tree[id].max = max(tree[id << 1].max, tree[id << 1 | 1].max);
			}
		}

		void pushdown(int id) {
			if (tree[id].l != tree[id].r) {
				tree[id << 1].max = tree[id].lazy;
				tree[id << 1 | 1].max = tree[id].lazy;
			}
		}

		int max(int a, int b) {
			return a > b ? a : b;
		}
	}

}
